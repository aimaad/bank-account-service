package org.sid.bank_account_service.entities;

import org.sid.bank_account_service.entities.BankAccount;
import org.sid.bank_account_service.enums.AccountType;
import org.springframework.data.rest.core.config.Projection;

import java.lang.reflect.Type;

@Projection(types = BankAccount.class , name = "p1")
public interface AccountProjection {
    public String getId();
    public AccountType getType();

}
