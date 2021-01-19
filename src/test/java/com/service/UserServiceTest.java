package com.service;

import com.entity.User;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserServiceTest {

    @Test
    public void findMore() {
        User user = new User();
        user.setUid(1);
        List<User> list = new UserService().findMore(user);
        list.forEach(System.out::println);
    }

    @Test
    public void insertADD() {
    }

    @Test
    public void updateUser() {
    }
}
