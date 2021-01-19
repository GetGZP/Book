package com.dao;

import com.entity.Address;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddressDAOMapper {

    /**
     * 根据uid或者adid或者全部查询
     * @param address 传入参数
     * @return 返回list
     */
    List<Address> findMore(Address address);

    /**
     * 根据传入的参数新增一条信息
     * @param address 传入address
     * @return 返回boolean
     */
    boolean insertADD(Address address);

    /**
     * 根据传入ID删除一条信息
     * @param adid 传入ID
     * @return 返回bolean
     */
    boolean deleteID(@Param("adid") int adid);

    /**
     * 批量删除
     * @param args 传入adid
     * @return 返回boolean
     */
    boolean deleteIDmore(String ...args);

    /**
     * 根据传入参数修改一条订单信息  根据adid
     * @param address  传入 address
     * @return 返回Boolean
     */
    boolean updateID(Address address);



}
