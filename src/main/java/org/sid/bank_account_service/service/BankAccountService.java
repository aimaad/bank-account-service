package org.sid.bank_account_service.service;

import org.sid.bank_account_service.dto.BankAccountRequestDto;
import org.sid.bank_account_service.dto.BankAccountResponseDto;
import org.sid.bank_account_service.entities.BankAccount;

public interface BankAccountService {
   public BankAccountResponseDto addAccount(BankAccountRequestDto bankAccountRequestDto);
   boolean deleteAccount(String id);

}
