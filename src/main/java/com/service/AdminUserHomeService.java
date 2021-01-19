package com.service;

import com.dao.AdminuserDAOMapper;
import com.entity.Adminuser;
import com.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

public class AdminUserHomeService {

    private static SqlSession session;

    /**
     * 根据用户名查询出一条数据
     * @param auser
     * @return
     */
    public Adminuser findMore(String auser){
        session = MyBatisUtil.sqlSessionFactory.openSession();
        AdminuserDAOMapper mapper = session.getMapper(AdminuserDAOMapper.class);
        Adminuser adminuser = mapper.findMore(auser);
        session.close();
        return adminuser;
    }
}
