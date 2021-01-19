package com.dao;

import com.entity.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookDAOMapper {

    /**
     * 查询所有书籍信息,也可以传入书籍名称或者商品类型进行模糊查询
     * @param book 传入书籍名称或者商品类型
     * @return 返回list
     */
    List<Book> findMore(Book book);

    /**
     * 根据传入的参数去修改一条指定的数据
     * @param book 传入要修改的数据
     * @return 返回Boolean
     */
    boolean updateID(Book book);

    /**
     * 根据传入的ID去修改书籍状态
     * @param book 传入书籍ID和状态
     * @return 返回Boolean
     */
    boolean upbstate(Book book);

    /**
     * 根据传入的数据新增一条书籍信息
     * @param book 传入book
     * @return 返回Boolean
     */
    boolean insertADD(Book book);

    /**
     * 根据传入的数据批量修改书籍状态
     * @param bstate 传入状态
     * @param bid 传入数组ID
     * @return 返回boolean
     */
    boolean stateupdates(@Param("bstate") String bstate, @Param("bid") String ...bid);

    /**
     * 查询热卖商品
     * @return 返回list
     */
    List<Book> findegroup();
}
