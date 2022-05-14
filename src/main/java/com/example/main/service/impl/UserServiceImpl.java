package com.example.main.service.impl;

import com.example.main.enums.ResultEnum;
import com.example.main.exception.Exception;
import com.example.main.model.Cart;
import com.example.main.model.User;
import com.example.main.repository.CartRepository;
import com.example.main.repository.UserRepository;
import com.example.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@DependsOn("passwordEncoder")
public class UserServiceImpl implements UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CartRepository cartRepository;


    @Override
    public User findOne(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public User createUser(User user) {
        //Register
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            User saveUser = userRepository.save(user);
            Cart saveCart = cartRepository.save(new Cart(saveUser));
            saveUser.setCart(saveCart);
            return userRepository.save(saveUser);
        } catch (java.lang.Exception e) {
            throw new Exception(ResultEnum.VALID_ERROR);
        }


    }

    @Override
    @Transactional
    public User updateUser(User user) {
        User existingUser = userRepository.findByEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setName(user.getName());
        existingUser.setPhone(user.getPhone());
        existingUser.setAddress(user.getAddress());
        return userRepository.save(existingUser);

    }

    @Override
    @Transactional
    public void deleteUser(String email) {
        User user =  findOne(email);

        if (user == null) throw new Exception(ResultEnum.USER_NOT_FOUND);

        userRepository.deleteByEmail(email);
    }
}
