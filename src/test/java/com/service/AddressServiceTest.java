package com.service;

import com.entity.Address;
import com.entity.User;
import org.junit.Test;

import java.util.List;

public class AddressServiceTest {

    @Test
    public void findMore() {
        Address address = new Address();
        User user = new User();
        address.setUser(user);
        List<Address> more = new AddressService().findMore(address);
        more.forEach(System.out::println);
    }

    @Test
    public void insertADD() {
        Address address = new Address();
        User user = new User();
        user.setUid(1);
        address.setUser(user);
    }

    @Test
    public void deleteID() {
    }

    @Test
    public void deleteIDmore() {
    }

    @Test
    public void updateID() {
    }
}
