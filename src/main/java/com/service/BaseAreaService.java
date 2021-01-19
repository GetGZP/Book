package com.service;

import com.dao.BaseAreaDAOMapper;
import com.entity.BaseArea;
import com.util.MyBatisUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class BaseAreaService {

    private static SqlSession session;

    /**
     * 查询省
     * @return 返回list
     */
    public List<BaseArea> findProvince(){
        session = MyBatisUtil.sqlSessionFactory.openSession();
        BaseAreaDAOMapper mapper = session.getMapper(BaseAreaDAOMapper.class);
        List<BaseArea> list = mapper.findProvince();
        session.commit();
        session.close();
        return list;
    }

    /**
     * 查询市
     * @param id 传入id
     * @return 返回list
     */
    public List<BaseArea> findCity(int id){
        session = MyBatisUtil.sqlSessionFactory.openSession();
        BaseAreaDAOMapper mapper = session.getMapper(BaseAreaDAOMapper.class);
        List<BaseArea> list = mapper.findCity(id);
        session.commit();
        session.close();
        return list;
    }

    /**
     * 查询地区
     * @param id 传入id
     * @return 返回list
     */
    public  List<BaseArea> findCounty(int id){
        session = MyBatisUtil.sqlSessionFactory.openSession();
        BaseAreaDAOMapper mapper = session.getMapper(BaseAreaDAOMapper.class);
        List<BaseArea> list = mapper.findCounty(id);
        session.commit();
        session.close();
        return list;
    }
}
