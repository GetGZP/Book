package com.dao;

import com.entity.BaseArea;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseAreaDAOMapper {

    /**
     * 查询省
     * @return 返回lis
     */
    List<BaseArea> findProvince();


    /**
     * 查询市
     * @param id 传入id
     * @return 返回List
     */
    List<BaseArea> findCity(@Param("id") int id);

    /**
     * 查询县区
     * @param id 传入id
     * @return 返回List
     */
    List<BaseArea> findCounty(@Param("id") int id);
}
