package org.sid.bank_account_service.mappers;

import org.sid.bank_account_service.dto.BankAccountRequestDto;
import org.sid.bank_account_service.entities.BankAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class AccountMapper {

    public BankAccount fromBankRequestDto(BankAccountRequestDto bankAccountRequestDto){

        BankAccount bankAccount = new BankAccount();
        BeanUtils.copyProperties(bankAccountRequestDto ,bankAccount );
        bankAccount.setId(UUID.randomUUID().toString());
        bankAccount.setCreatedAt(new Date());

return  bankAccount;

    }

    public BankAccountRequestDto fromBankAccount(BankAccount bankAccount)
    {
        BankAccountRequestDto bankAccountRequestDto = new BankAccountRequestDto();
        BeanUtils.copyProperties(bankAccount,bankAccountRequestDto );
        return bankAccountRequestDto;

    }



}
