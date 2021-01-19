package com.dao;

import com.entity.ShopOrderTable;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopOrderTableDAOMapper {

    /**
     * 根据传入的数据批量添加
     * @param shopOrderTableList 集合
     * @return 返回Boolean
     */
    boolean insertADDMore(List<ShopOrderTable> shopOrderTableList);

    /**
     * 根据订单编号进行查询
     * @param onumbers
     * @return
     */
    List<ShopOrderTable> findByMore(@Param("onumbers") int onumbers);
}
