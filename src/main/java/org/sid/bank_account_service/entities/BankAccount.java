package org.sid.bank_account_service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.bank_account_service.enums.AccountType;

import java.util.Date;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder

public class BankAccount {
    @Id
    private String id;
    private Date createdAt;
    private Double balance;
    private String Currency;
    @Enumerated(EnumType.STRING)
    private AccountType type;
    @ManyToOne
    private Customer customer;
}
