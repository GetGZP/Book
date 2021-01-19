package com.service;

import com.dao.UserDAOMapper;
import com.entity.Address;
import com.entity.User;
import com.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class UserService {

    private static SqlSession session;

    /**
     * 查询可以是条件查询也可以是全部查询
     * @param user 传入条件
     * @return 返回List
     */
    public List<User> findMore(User user){
        session = MyBatisUtil.sqlSessionFactory.openSession();
        UserDAOMapper mapper = session.getMapper(UserDAOMapper.class);
        if(user == null){
            user = new User();
        }
        List<User> list = mapper.findMore(user);
        session.close();
        return list;
    }

    /**
     * 新增一条数据
     * @param user 传入user
     * @return 返回Boolean
     */
    public boolean insertADD(User user){
        session = MyBatisUtil.sqlSessionFactory.openSession();
        UserDAOMapper mapper = session.getMapper(UserDAOMapper.class);
        boolean b = mapper.insertADD(user);
        if(b){
            session.commit();
        }else {
            session.rollback();
        }
        session.close();
        return b;
    }

    /**
     * 根据传入的参数修改一条数据
     * @param user 传入参数
     * @return 返回Boolean
     */
    public boolean updateUser(User user){
        session = MyBatisUtil.sqlSessionFactory.openSession();
        UserDAOMapper mapper = session.getMapper(UserDAOMapper.class);
        if(user.getAddress()==null){
            user.setAddress(new Address());
        }
        boolean b = mapper.updateUser(user);
        if(b){
            session.commit();
        }else {
            session.rollback();
        }
        session.close();
        return b;
    }
}
