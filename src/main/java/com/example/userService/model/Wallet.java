package com.example.userService.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "u_wallet")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long walletId;

    private BigDecimal balance;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    //@JsonBackReference
    private User user;

    public Wallet(BigDecimal balance, User user) {
        this.balance = balance;
        this.user = user;
    }
}
