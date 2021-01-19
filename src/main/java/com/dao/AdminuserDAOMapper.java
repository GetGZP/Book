package com.dao;

import com.entity.Adminuser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminuserDAOMapper {

    /**
     * 查询管理员表中的所有数据
     * @return 返回List
     */
    List<Adminuser> findMore();

    /**
     * 根据用户名查询数据
     * @param auser 传入用户名
     * @return 返回一条参数
     */
    Adminuser findMore(@Param("auser") String auser);
}
