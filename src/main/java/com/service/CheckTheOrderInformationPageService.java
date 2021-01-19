package com.service;

import com.dao.OrderListDAOMapper;
import com.entity.Address;
import com.entity.OrderList;
import com.entity.User;
import com.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

public class CheckTheOrderInformationPageService {

    private static SqlSession session;

    /**
     * 修改订单状态
     * @param orderList
     * @return
     */
    public boolean updateMore(OrderList orderList){
        session = MyBatisUtil.sqlSessionFactory.openSession();
        OrderListDAOMapper mapper = session.getMapper(OrderListDAOMapper.class);
        if (orderList.getUser() == null){
            orderList.setUser(new User());
        }
        if(orderList.getAddress() == null){
            orderList.setAddress(new Address());
        }
        boolean b = mapper.updateMore(orderList);
        if (b){
            session.commit();
        }else {
            session.rollback();
        }
        session.close();
        return b;
    }
}
