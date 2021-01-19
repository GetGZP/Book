package com.service;

import com.dao.AdminuserDAOMapper;
import com.entity.Adminuser;
import com.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class AdminUserService {

    private static SqlSession session;

    /**
     * 查询list表中的所有数据
     * @return 返回list
     */
    public List<Adminuser> findMore(){
        session = MyBatisUtil.sqlSessionFactory.openSession();
        AdminuserDAOMapper mapper = session.getMapper(AdminuserDAOMapper.class);
        List<Adminuser> list = mapper.findMore();
        session.close();
        return list;
    }
}
