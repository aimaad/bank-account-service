package org.sid.bank_account_service.web;
import org.sid.bank_account_service.dto.BankAccountRequestDto;
import org.sid.bank_account_service.dto.BankAccountResponseDto;
import org.sid.bank_account_service.entities.BankAccount;
import org.sid.bank_account_service.enums.AccountType;
import org.sid.bank_account_service.repositories.BankAccountRepository;
import org.sid.bank_account_service.service.BankAccountService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.Date;
import java.util.List;
@Controller
public class AccountGraphqlController {
    BankAccountRepository bankAccountRepository;
    BankAccountService bankAccountService;

    public AccountGraphqlController(BankAccountRepository bankAccountRepository, BankAccountService bankAccountService) {
        this.bankAccountRepository = bankAccountRepository;
        this.bankAccountService = bankAccountService;
    }


    @QueryMapping
    List<BankAccount> listAccounts()
    {
        return bankAccountRepository.findAll();
    }
    @QueryMapping
    List<BankAccount> searchByType(@Argument AccountType typeAccount) {
        return bankAccountRepository.findByType(typeAccount);
    }
    @QueryMapping
    List<BankAccount>  findByBalanceLessThan(@Argument Float balance) {
        return bankAccountRepository.findByBalanceLessThan(balance.doubleValue());
    }
    @QueryMapping
    BankAccount accountById(@Argument String id)
    {
        return bankAccountRepository.findById(id).orElseThrow(()->new RuntimeException(String.format("Account %s not found",id)));
    }
    @MutationMapping
    BankAccountResponseDto  addAccount(@Argument BankAccountRequestDto bankAccountRequestDto)
    {
       return bankAccountService.addAccount(bankAccountRequestDto);
    }
    @MutationMapping
  BankAccountResponseDto updateAccount(@Argument String id,@Argument BankAccountRequestDto bankAccountRequestDto)
    {
        BankAccount bankAccount = new BankAccount();
        bankAccount=bankAccountRepository.findById(id).orElseThrow(()->new RuntimeException(String.format("Account %s not found",id)));
        bankAccount.setCurrency(bankAccountRequestDto.getCurrency());
        bankAccount.setBalance(bankAccountRequestDto.getBalance());
        bankAccount.setType(bankAccountRequestDto.getType());
        bankAccount.setCreatedAt(new Date());

        bankAccountRepository.save(bankAccount);

        return BankAccountResponseDto.builder()
                .id(bankAccount.getId())
                .balance(bankAccount.getBalance())
                .createdAt(bankAccount.getCreatedAt())
                .Currency(bankAccount.getCurrency())
                .type(bankAccount.getType())
                .build();
    }
    @MutationMapping
    boolean  deleteAccount(@Argument String id)
    {

        return bankAccountService.deleteAccount(id);
    }





}
