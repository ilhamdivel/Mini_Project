package com.example.main;


import com.example.main.service.impl.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        UserServiceImplTest.class,
        ProductServiceImplTest.class
})
public class MainApplicationTests {
}
