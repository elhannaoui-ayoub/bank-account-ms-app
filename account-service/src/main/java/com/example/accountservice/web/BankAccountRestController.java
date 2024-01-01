package com.example.accountservice.web;

import com.example.accountservice.clients.CustomerRestClient;
import com.example.accountservice.entities.BankAccount;
import com.example.accountservice.model.Customer;
import com.example.accountservice.repository.BankAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class BankAccountRestController {
    private BankAccountRepository bankAccountRepository;
    private CustomerRestClient customerRestClient;

    @GetMapping("/accounts")
    public List<BankAccount> accountsList(){
        List<BankAccount> bankAccounts = bankAccountRepository.findAll();
        bankAccounts.forEach(
                acc->{
                    acc.setCustomer(customerRestClient.findCustomerById(acc.getCustomerId()));
                }
        );
        return bankAccounts;
    }

    @GetMapping("/accounts/{id}")
    public BankAccount accountById(@PathVariable String id){

        BankAccount bankAccount= bankAccountRepository.findById(id).get();
        Customer customer = customerRestClient.findCustomerById(bankAccount.getCustomerId());
        bankAccount.setCustomer(customer);
        return bankAccount;

    }


}
