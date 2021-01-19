package com.dao;

import com.entity.BookType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookTypeDAOMapper {

    /**
     * 查询书籍类型所有信息
     * @return 返回List
     */
    List<BookType> findMore();

    /**
     * 条件查询可以任意条件
     * @param bookType 传入条件
     * @return 返回list
     */
    List<BookType> findBY(BookType bookType);

    /**
     * 根据传入的id删除一条信息
     * @param tid 传入id
     * @return 返回Boolean
     */
    boolean deleteID(@Param("tid") int tid);

    /**
     * 根据传入的数据修改一条信息
     * @param bookType 传入数据
     * @return 返回boolean
     */
    boolean updateID(BookType bookType);

    /**
     * 根据传入的数据新增一条信息
     * @param bookType 传入booktype
     * @return 返回boolean
     */
    boolean insertADD(BookType bookType);
}
