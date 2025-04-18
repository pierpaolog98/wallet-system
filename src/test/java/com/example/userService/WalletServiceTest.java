package com.example.userService;

import com.example.userService.model.User;
import com.example.userService.model.Wallet;
import com.example.userService.repository.WalletRepository;
import com.example.userService.service.WalletService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WalletServiceTest {

    @Mock
    private WalletRepository walletRepository;

    @InjectMocks
    private WalletService walletService;

    @Test
    void whenWalletIsCreated_thenItHasZeroBalanceAndLinkedUser(){
        User user = new User("pier","giamp","pier@example.com","pierGiamp");
        Wallet wallet = new Wallet(BigDecimal.ZERO, user);
        when(walletRepository.save(any(Wallet.class))).thenAnswer(walletSaved -> {
            Wallet wallet1 = walletSaved.getArgument(0);
            System.out.println("Mocking wallet save: " + wallet1);
            wallet1.setWalletId(1L);
            return wallet1;
        });
        Wallet createdWallet = walletService.createWallet(user);
        verify(walletRepository).save(argThat(w ->
                w.getWalletId().equals(createdWallet.getWalletId()) &&
                        w.getBalance().compareTo(createdWallet.getBalance()) == 0 &&
                        w.getUser().equals(createdWallet.getUser())
        ));
    }



}
