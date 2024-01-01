package com.example.accountservice;

import com.example.accountservice.clients.CustomerRestClient;
import com.example.accountservice.entities.BankAccount;
import com.example.accountservice.enums.AccountType;
import com.example.accountservice.repository.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class AccountServiceApplication {


	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(BankAccountRepository bankAccountRepository, CustomerRestClient customerRestClient){

		return args -> {
			customerRestClient.allCustomers().forEach(
					c->{
						BankAccount bankAccount = BankAccount.builder()
								.id(UUID.randomUUID().toString())
								.balance(Math.random()*80000)
								.currency("MAD")
								.createdAt(LocalDate.now())
								.type(AccountType.CURRENT_ACCOUNT)
								.customerId(Long.valueOf(c.getId()))
								.build();
						bankAccountRepository.save(bankAccount);
					}
			);


		};
	}
}
