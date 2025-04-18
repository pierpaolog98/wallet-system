package com.example.userService;

import com.example.userService.dto.UserDTO;
import com.example.userService.model.User;
import com.example.userService.model.Wallet;
import com.example.userService.repository.UserRepository;
import com.example.userService.repository.WalletRepository;
import com.example.userService.service.UserService;
import com.example.userService.service.WalletService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private WalletService walletService;

    @InjectMocks
    private UserService userService;


    @Test
    void whenUserIsCreated_thenWalletServiceIsCalled(){
        UserDTO userDTO = new UserDTO("pier","giamp","pier@example.com","pierGiamp");
        User user = new User("pier","giamp","pier@example.com","pierGiamp");
        Wallet wallet = new Wallet(BigDecimal.ZERO, user);
        when(userRepository.save(any(User.class))).thenAnswer(userSaved -> {
            User user1 = userSaved.getArgument(0);
            user1.setId(1L);
            System.out.println("Mocking wallet save: " + user1);
            return user1;
        });
        when(walletService.createWallet(any(User.class))).thenReturn(wallet);
        Long userId = userService.createUser(userDTO);
        assertNotNull(userId);
        verify(userRepository).save(argThat(savedUser ->
                savedUser.getEmail().equals("pier@example.com") &&
                        savedUser.getName().equals("pier") &&
                        savedUser.getSurname().equals("giamp") &&
                        savedUser.getPassword().equals("pierGiamp")
        ));
        verify(walletService).createWallet(any(User.class));
    }


}
