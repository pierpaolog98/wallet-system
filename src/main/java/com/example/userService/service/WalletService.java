package com.example.userService.service;

import com.example.userService.model.User;
import com.example.userService.model.Wallet;
import com.example.userService.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;

    public Wallet createWallet(User user){
        Wallet wallet = new Wallet(BigDecimal.ZERO, user);
        walletRepository.save(wallet);
        System.out.println("Wallet: " + wallet);
        return wallet;
    }

}
