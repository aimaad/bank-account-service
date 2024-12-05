package org.sid.bank_account_service.web;

import org.sid.bank_account_service.dto.BankAccountRequestDto;
import org.sid.bank_account_service.dto.BankAccountResponseDto;
import org.sid.bank_account_service.entities.BankAccount;
import org.sid.bank_account_service.repositories.BankAccountRepository;
import org.sid.bank_account_service.service.BankAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class AccountRestController {


    BankAccountRepository bankAccountRepository;
    BankAccountService bankAccountService;

    public AccountRestController(BankAccountRepository bankAccountRepository, BankAccountService bankAccountService) {
        this.bankAccountRepository = bankAccountRepository;
        this.bankAccountService = bankAccountService;
    }


    @GetMapping("/bankAccounts")
    public List<BankAccount> bankAccounts()
    {
        return  bankAccountRepository.findAll();

    }
    @GetMapping("/bankAccounts/{id}")
    public BankAccount bankAccount(@PathVariable String id)
    {
        return bankAccountRepository.findById(id).orElseThrow(()->new RuntimeException(String.format("Account %s Not found",id)));
    }
    @PostMapping("/bankAccounts")
    public BankAccountResponseDto save(@RequestBody BankAccountRequestDto bankAccountRequestDto)

    {
    return bankAccountService.addAccount(bankAccountRequestDto);

    }
    @PutMapping("/bankAccounts/{id}")
    public BankAccount update(@PathVariable String id,@RequestBody BankAccount bankAccount )

    {
     BankAccount bankAccount1 = bankAccountRepository.findById(id).orElseThrow(()->new RuntimeException(String.format("Account %s Not found",id)));

     if(bankAccount.getBalance()!=null) bankAccount1.setBalance(bankAccount.getBalance());
     if(bankAccount.getType()!=null)  bankAccount1.setType(bankAccount.getType());
     if(bankAccount.getBalance()!=null)  bankAccount1.setCurrency(bankAccount.getCurrency());
     if(bankAccount.getCreatedAt()!=null) bankAccount1.setCreatedAt(new Date());
     if(bankAccount.getCurrency()!=null) bankAccount1.setCurrency(bankAccount.getCurrency());

     return bankAccountRepository.save(bankAccount1);

    }

    @DeleteMapping("/bankAccounts/{id}")
    public void deleteAccount(@PathVariable String id )

    {
bankAccountRepository.deleteById(id);

    }
}
