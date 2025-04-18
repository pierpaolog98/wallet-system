package com.example.userService.mapper;

import com.example.userService.dto.UserDTO;
import com.example.userService.model.User;
import org.modelmapper.ModelMapper;

public class UserMapper {

    public static User toEntity(UserDTO userDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userDTO, User.class);
    }

}
