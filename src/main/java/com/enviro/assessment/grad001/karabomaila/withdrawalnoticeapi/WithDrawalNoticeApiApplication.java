package com.enviro.assessment.grad001.karabomaila.withdrawalnoticeapi;

import com.enviro.assessment.grad001.karabomaila.withdrawalnoticeapi.model.Address;
import com.enviro.assessment.grad001.karabomaila.withdrawalnoticeapi.model.Investor;
import com.enviro.assessment.grad001.karabomaila.withdrawalnoticeapi.model.Product;
import com.enviro.assessment.grad001.karabomaila.withdrawalnoticeapi.service.InvestorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
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
            address.setStreet("Street");
            address.setProvince("Gauteng");
            address.setPostalCode("4113");
            address.setHouseNumber("3384");
            address.setSuburb("City Power");
            investor.setAddress(address);
            investor.setDateOfBirth(LocalDateTime.now().toLocalDate());
            investor.setAge(73);
            investor.setEmail("karabo.maila");
            investor.setContact("093383839");

            Product product = new Product();
            product.setType("SAVINGS");
            product.setName("Saving product");
            product.setCurrentBalance(90000);
            product.setInvestor(investor);
            List<Product> products = new ArrayList<>();
            products.add(product);
            investor.setProducts(products);
            investor.setFirstName("Karabo");
            investor.setLastName("Maila");

            investorService.addInvestor(investor);

            Investor investor2 = new Investor();
            Address address2 = new Address();
            address2.setStreet("Street");
            address2.setPostalCode("4222");
            address2.setProvince("Gauteng");
            address2.setHouseNumber("3384");
            address2.setSuburb("City Power");
            investor2.setAddress(address2);
            investor2.setDateOfBirth(LocalDateTime.now().toLocalDate());
            investor2.setAge(23);
            investor2.setEmail("karabo.maila");
            investor2.setContact("09338333839");
            investor2.setFirstName("Karabo");
            investor2.setLastName("Maila");
            investorService.addInvestor(investor2);

            Investor investor3 = new Investor();
            Address address3 = new Address();
            address3.setStreet("Street 554");
            address3.setProvince("Gauteng");
            address3.setPostalCode("4433");
            address3.setHouseNumber("33584");
            address3.setSuburb("City Power");
            investor3.setAddress(address3);
            investor3.setDateOfBirth(LocalDateTime.now().toLocalDate());
            investor3.setAge(43);
            investor3.setEmail("john.doe");
            investor3.setContact("09338333");
            investor3.setFirstName("John");
            investor3.setLastName("Doe");
            investorService.addInvestor(investor3);
        };
    }
}
