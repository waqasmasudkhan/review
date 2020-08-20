package com.udacity.jwdnd.c1.review.service;

import com.udacity.jwdnd.c1.review.mapper.UserMapper;
import com.udacity.jwdnd.c1.review.model.Users;
import com.udacity.jwdnd.c1.review.service.HashService;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;


@Service
public class UserService {
    private final UserMapper userMapper;
    private final HashService hashService;

    public UserService(UserMapper userMapper, HashService hashService){

        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    public String getUsername(String username){
        return userMapper.getUsername(username);
    }

    public boolean isUsernameAvailable(String username){
        return userMapper.getUsername(username)==null;
    }

    public void createUser(Users user){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
        userMapper.addUser(new Users(null,user.getUserName(),encodedSalt,hashedPassword,user.getFirstName(),user.getLastName()));
    }

    public Users getUser(String userName){
        return userMapper.getUser(userName);
    }

}
