package com.example.userService.service;

import com.example.userService.dto.UserDTO;
import com.example.userService.mapper.UserMapper;
import com.example.userService.model.User;
import com.example.userService.model.Wallet;
import com.example.userService.repository.UserRepository;
import com.example.userService.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;
    private final WalletService walletService;

    @Transactional
    public Long createUser(UserDTO userDTO){
        User user = UserMapper.toEntity(userDTO);
        userRepository.save(user);
        walletService.createWallet(user);
        return user.getId();
    }

}

