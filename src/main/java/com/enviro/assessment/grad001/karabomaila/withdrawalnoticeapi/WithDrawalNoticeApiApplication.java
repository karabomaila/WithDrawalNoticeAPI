package com.enviro.assessment.grad001.karabomaila.withdrawalnoticeapi;

import com.enviro.assessment.grad001.karabomaila.withdrawalnoticeapi.model.Address;
import com.enviro.assessment.grad001.karabomaila.withdrawalnoticeapi.model.Investor;
import com.enviro.assessment.grad001.karabomaila.withdrawalnoticeapi.model.Product;
import com.enviro.assessment.grad001.karabomaila.withdrawalnoticeapi.service.InvestorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class WithDrawalNoticeApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(WithDrawalNoticeApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(InvestorService investorService){
        return runner->{
            Investor investor = new Investor();
            Address address = new Address();
            address.setStreet("djdjdjd");
            address.setProvince("Gauteng");
            address.setHouseNumber("3384");
            address.setSuburb("City Power");
            investor.setAddress(address);
            investor.setAge(73);
            investor.setEmail("karabo.maila");
            investor.setContact("093383839");

            Product product = new Product();
            product.setType("SAVINGS");
            product.setName("Satrix Saving");
            product.setCurrentBalance(90000);
            product.setInvestor(investor);
            List<Product> products = new ArrayList<>();
            products.add(product);
            investor.setProducts(products);
            investor.setFirstName("Karabo");
            investor.setLastName("Maila");

            investorService.addInvestor(investor);
        };
    }
}
