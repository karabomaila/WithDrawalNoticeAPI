package com.enviro.assessment.grad001.karabomaila.withdrawalnoticeapi.api;

import com.enviro.assessment.grad001.karabomaila.withdrawalnoticeapi.Util.CSVFile;
import com.enviro.assessment.grad001.karabomaila.withdrawalnoticeapi.model.BankAccountInfo;
import com.enviro.assessment.grad001.karabomaila.withdrawalnoticeapi.model.Investor;
import com.enviro.assessment.grad001.karabomaila.withdrawalnoticeapi.model.WithDrawalNotice;
import com.enviro.assessment.grad001.karabomaila.withdrawalnoticeapi.service.InvestorService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.ValidationErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class NoticeAPI {
    private InvestorService investorService;

    @Autowired
    public NoticeAPI(InvestorService investorService) {
        this.investorService = investorService;
    }

    @GetMapping("/investor")
    public Optional<Investor> retrieveInvestor(@RequestParam(required = true) long investorId){
        return investorService.findInvestorById(investorId);
    }

    @PostMapping("/notice")
    public ResponseEntity createNotice(@RequestParam(required = true) long investorId,
                                       @RequestParam(required = true) String productType,
                                       @RequestParam(required = true) double amount,
                                       @RequestParam(required = true) LocalDate date,
                                       @RequestBody(required = true) BankAccountInfo bankAccountInfo) throws Exception {
        Optional<Investor> investor = investorService.findInvestorById(investorId);
        // check if the productType is correct and if the investor is allowed withdraw money
        if (productType.equalsIgnoreCase("SAVINGS") || productType.equalsIgnoreCase("RETIREMENT")){
            if (investor.isEmpty()) throw new Exception("The investor is not found");
            else if (investor.get().getAge() <= 65){
                throw new Exception("Not in a retirement age");
            }
            else if (investor.get().isAmountGreater(productType, amount)){
                throw new Exception("The amount is greater than the current balance or the select product is not in the investor account");
            }
        }
        else{
            throw new Exception("Selected Product not found");
        }

        // create the notice
        WithDrawalNotice withDrawalNotice = new WithDrawalNotice();
        withDrawalNotice.setAmount(amount);
        withDrawalNotice.setWithDrawalDate(date);
        withDrawalNotice.setBankAccountInfo(bankAccountInfo);
        withDrawalNotice.setProduct(investor.get().getProductByType(productType));
        withDrawalNotice.setProductType(productType);
        investorService.addNotice(withDrawalNotice);

        return new ResponseEntity<>("The notice was created", HttpStatus.OK);
    }

    @GetMapping("/statements")
    public void downloadStatement(HttpServletResponse response, @RequestParam(required = true) String productType, @RequestParam(required = true) LocalDate startDate, @RequestParam(required = true) LocalDate endDate) throws IOException {
        List<WithDrawalNotice> withDrawalNotices = investorService.findWithDrawalNoticeByDateBetween(productType, startDate, endDate);

        response.setContentType("text/csv");
        response.addHeader("Content-Disposition", "attachment; filename=\"withdrawalnotices.csv\"");
        CSVFile.createCSVFileOfNotices(response.getWriter(), withDrawalNotices);
    }

}
