package com.example.main.service.impl;

import  com.example.main.model.User;
import com.example.main.repository.CartRepository;
import com.example.main.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private CartRepository cartRepository;

    private User user;

    @Before
    public void setUp() {
        user = new User();
        user.setPassword("ilham21");
        user.setEmail("ilham@email.com");
        user.setName("ilham");
        user.setPhone("0821");
        user.setAddress("Padang");
    }

    @Test
    public void findByEmailTest() {
        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);

        userService.findOne(user.getEmail());

        verify(userRepository, times(1)).findByEmail(user.getEmail());

    }


    @Test
    public void createUserTest() {
        when(userRepository.save(user)).thenReturn(user); //stubbing

        userService.createUser(user);

        verify(userRepository, times(2)).save(user); //verification

    }

    @Test
    public void updateUserTest() {
        User user = new User();
        user.setName("Ilham Edit");

        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);

        User updateUser = userService.updateUser(user);

        assertSame(updateUser.getEmail(), (user.getEmail()));
    }

    @Test
    public void deleteUserTest(){
        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);

        userService.deleteUser(user.getEmail());

        verify(userRepository,times(1)).deleteByEmail(user.getEmail());
    }

}



