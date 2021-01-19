package com.service;

import com.dao.AddressDAOMapper;
import com.entity.Address;
import com.entity.User;
import com.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class AddressService {

    private static SqlSession session;

    /**
     * 根据传入的参数进行查询
     * @param address 传入参数或者null
     * @return 返回list
     */
    public List<Address> findMore(Address address){
        session = MyBatisUtil.sqlSessionFactory.openSession();
        AddressDAOMapper mapper = session.getMapper(AddressDAOMapper.class);
        if(address == null){
            address = new Address();
            User user = new User();
            address.setUser(user);
        }else if(address.getUser()== null){
            User user = new User();
            address.setUser(user);
        }
        List<Address> list = mapper.findMore(address);
        session.commit();
        session.close();
        return list;
    }

    /**
     * 根据传入的参数新增一条信息
     * @param address 传入参数
     * @return 返回boolean
     */
    public boolean insertADD(Address address){
        session = MyBatisUtil.sqlSessionFactory.openSession();
        AddressDAOMapper mapper = session.getMapper(AddressDAOMapper.class);
        boolean b = mapper.insertADD(address);
        if (b){
            session.commit();
        }else {
            session.rollback();
        }
        session.close();
        return b;
    }

    /**
     * 根据传入的ID删除一条数据
     * @param adid 传入adid
     * @return 返回Boolean
     */
    public boolean deleteID(int adid){
        session = MyBatisUtil.sqlSessionFactory.openSession();
        AddressDAOMapper mapper = session.getMapper(AddressDAOMapper.class);
        boolean b = mapper.deleteID(adid);
        if (b){
            session.commit();
        }else {
            session.rollback();
        }
        session.close();
        return b;
    }

    /**
     * 批量删除
     * @param args 传入ID数组
     * @return 返回boolean
     */
    public boolean deleteIDmore(String ...args){
        session = MyBatisUtil.sqlSessionFactory.openSession();
        AddressDAOMapper mapper = session.getMapper(AddressDAOMapper.class);
        boolean b = mapper.deleteIDmore(args);
        if (b){
            session.commit();
        }else {
            session.rollback();
        }
        session.close();
        return b;
    }

    /**
     * 根据传入的参数修改一条数据
     * @param address 传入参数
     * @return 返回boolean
     */
    public boolean updateID(Address address){
        session = MyBatisUtil.sqlSessionFactory.openSession();
        AddressDAOMapper mapper = session.getMapper(AddressDAOMapper.class);
        boolean b = mapper.updateID(address);
        if (b){
            session.commit();
        }else {
            session.rollback();
        }
        session.close();
        return b;
    }
}
