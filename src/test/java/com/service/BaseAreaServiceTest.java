package com.service;

import com.entity.BaseArea;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BaseAreaServiceTest {

    @Test
    public void findProvince() {
        List<BaseArea> list = new BaseAreaService().findProvince();
        list.forEach(System.out::println);
    }

    @Test
    public void findCity() {
        List<BaseArea> list = new BaseAreaService().findCity(11);
        list.forEach(System.out::println);
    }

    @Test
    public void findCounty() {
        List<BaseArea> list = new BaseAreaService().findCounty(1101);
        list.forEach(System.out::println);
    }
}
