package com.enviro.assessment.grad001.karabomaila.withdrawalnoticeapi.api;

import com.enviro.assessment.grad001.karabomaila.withdrawalnoticeapi.Util.CSVFile;
import com.enviro.assessment.grad001.karabomaila.withdrawalnoticeapi.model.*;
import com.enviro.assessment.grad001.karabomaila.withdrawalnoticeapi.service.InvestorService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class InvestorAPI {
    private InvestorService investorService;

    @Autowired
    public InvestorAPI(InvestorService investorService) {
        this.investorService = investorService;
    }

    @GetMapping("/investor")
    public ResponseEntity retrieveInvestor(@RequestParam(required = true) long investorId){
        Optional<Investor> investor = investorService.findInvestorById(investorId);
        if (investor.isEmpty()) return new ResponseEntity("The investor was not found", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(investor.get(), HttpStatus.OK);
    }

    @PostMapping("/investor")
    public ResponseEntity addInvestor(@RequestBody(required = true) Investor investor){
        investorService.addInvestor(investor);
        return new ResponseEntity("The investor information was added", HttpStatus.OK);
    }

    @PostMapping("/investor/product")
    public ResponseEntity addInvestorProduct(@RequestParam(required = true) long investorId, @RequestParam(required = true) String name, @RequestParam(required = true) String type, @RequestParam(required = true) double amount){
        Optional<Investor> investor = investorService.findInvestorById(investorId);
        if (amount <= 0) return new ResponseEntity("The given amount was invalid", HttpStatus.NOT_ACCEPTABLE);
        if (investor.isEmpty()) return new ResponseEntity("The investor was not found", HttpStatus.NOT_FOUND);
        else if (type.equalsIgnoreCase("SAVINGS") || type.equalsIgnoreCase("RETIREMENT")){
            Product product = new Product();
            if (investor.get().getProductByType(type) == null) {
                product.setInvestor(investor.get());
                product.setType(type.toUpperCase());
                product.setName(name);
                product.setCurrentBalance(amount);
                investor.get().addProduct(product);
            }
            else{
                investor.get().getProductByType(type).addToCurrentBalance(amount);
            }
            investorService.addInvestor(investor.get());
        }
        else{
            return new ResponseEntity("The selected product was not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity("The product was added", HttpStatus.OK);
    }

    @PostMapping("/notice")
    public ResponseEntity<Notification> createNotice(@RequestParam(required = true) long investorId,
                                     @RequestParam(required = true) String productType,
                                     @RequestParam(required = true) double amount,
                                     @RequestParam(required = true) LocalDate date,
                                     @RequestBody(required = true) BankAccountInfo bankAccountInfo) throws Exception {
        Optional<Investor> investor = investorService.findInvestorById(investorId);
        // check if the productType is correct and if the investor is allowed withdraw money
        if (amount <= 0) throw  new Exception("The given amount was negative");
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
        withDrawalNotice.setProductType(productType.toUpperCase());

        try {
            Notification notification = investorService.addNotice(withDrawalNotice);
            return new ResponseEntity(notification, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/statements")
    public void downloadStatement(HttpServletResponse response, @RequestParam(required = true) String productType, @RequestParam(required = true) LocalDate startDate, @RequestParam(required = true) LocalDate endDate) throws IOException {
        List<WithDrawalNotice> withDrawalNotices = investorService.findWithDrawalNoticeByDateBetween(productType, startDate, endDate);

        response.setContentType("text/csv");
        response.addHeader("Content-Disposition", "attachment; filename=\"withdrawalnotices.csv\"");
        CSVFile.createCSVFileOfNotices(response.getWriter(), withDrawalNotices);
    }

}
