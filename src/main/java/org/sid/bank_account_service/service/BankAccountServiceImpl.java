package org.sid.bank_account_service.service;


import org.sid.bank_account_service.dto.BankAccountRequestDto;
import org.sid.bank_account_service.dto.BankAccountResponseDto;
import org.sid.bank_account_service.entities.BankAccount;
import org.sid.bank_account_service.mappers.AccountMapper;
import org.sid.bank_account_service.repositories.BankAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class BankAccountServiceImpl implements BankAccountService{
    BankAccountRepository bankAccountRepository;
    AccountMapper accountMapper;

    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository, AccountMapper accountMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.accountMapper = accountMapper;
    }


    @Override
    public BankAccountResponseDto addAccount(BankAccountRequestDto bankAccountRequestDto) {
        BankAccount bankAccount = new BankAccount();
        bankAccount=accountMapper.fromBankRequestDto(bankAccountRequestDto);

        BankAccount  bankAccountSaved= bankAccountRepository.save(bankAccount);

        return BankAccountResponseDto.builder()
                .id(bankAccountSaved.getId())
                .balance(bankAccountSaved.getBalance())
                .createdAt(bankAccountSaved.getCreatedAt())
                .Currency(bankAccountSaved.getCurrency())
                .type(bankAccountSaved.getType())
                .build();


    }

    @Override
    public boolean deleteAccount(String id) {
        bankAccountRepository.deleteById(id);
        return true;
    }
}
