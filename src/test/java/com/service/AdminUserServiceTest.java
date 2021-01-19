package com.service;

import com.entity.Adminuser;
import org.junit.Test;

import java.util.List;

public class AdminUserServiceTest {

    /**
     * 测试查询管理员账户表中的所有数据
     */
    @Test
    public void findMore() {
        List<Adminuser> more = new AdminUserService().findMore();
        more.forEach(System.out::println);
    }
}
