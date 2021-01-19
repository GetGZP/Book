package com.service;

import com.dao.ShopOrderTableDAOMapper;
import com.entity.ShopOrderTable;
import com.util.MyBatisUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ShopOrderTableService {

    private static SqlSession session;

    /**
     * 根据订单编号查询
     * @param onumbers
     * @return
     */
    public List<ShopOrderTable> findByMore(int onumbers){
        SqlSession session = MyBatisUtil.sqlSessionFactory.openSession();
        ShopOrderTableDAOMapper mapper = session.getMapper(ShopOrderTableDAOMapper.class);
        List<ShopOrderTable> list = mapper.findByMore(onumbers);
        session.commit();
        session.close();
        return list;
    }
}
