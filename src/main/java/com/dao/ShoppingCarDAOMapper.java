package com.dao;

import com.entity.ShoppingCar;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShoppingCarDAOMapper {

    /**
     * 查询可以全部查询也可以查询一条
     * @param shoppingCar
     * @return
     */
   List<ShoppingCar> findMore(ShoppingCar shoppingCar);

    /**
     * 新增一条数据
     * @param shoppingCar
     * @return
     */
   boolean ADDCar(ShoppingCar shoppingCar);

    /**
     * 查询购物车中有几件商品
     * @param uid
     * @return
     */
   Integer findNumber(@Param("uid") int uid);

    /**
     * 根据传入的sid批量查询
     * @param sid
     * @return
     */
   List<ShoppingCar> findBySid(String ...sid);

    /**
     * 根据传入的参数删除一条信息
     * @param shoppingCar
     * @return
     */
   boolean deleteCar(ShoppingCar shoppingCar);

    /**
     * 根据传入的ID批量删除
     * @param sid
     * @return
     */
   boolean deleteCares(String ...sid);

    /**
     * 根据传入的参数修改一条数据
     * @param shoppingCar
     * @return
     */
   boolean updateCar(ShoppingCar shoppingCar);


    /**
     * 查询最新添加的sid
     * @return
     */
   Integer ADD();


}
