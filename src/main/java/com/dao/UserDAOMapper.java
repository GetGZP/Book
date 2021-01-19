package com.dao;

import com.entity.User;

import java.util.List;

public interface UserDAOMapper {

    /**
     * 查询可以跟查询全部也可以条件查询
     * @param user 传入查询参数
     * @return 返回List
     */
    List<User> findMore(User user);

    /**
     * 根据传入的参数新增一条数据
     * @param user 传入参数
     * @return 返回boolean
     */
    boolean insertADD(User user);

    /**
     * 根据传入的参数去修改数据
     * @param user 传入user
     * @return 返回Boolean
     */
    boolean updateUser(User user);
}
