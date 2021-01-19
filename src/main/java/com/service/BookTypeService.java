package com.service;

import com.dao.BookTypeDAOMapper;
import com.entity.BookType;
import com.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class BookTypeService {

    private static SqlSession session;

    /**
     * 查询书籍类型表中的所有信息
     * @return 返回list
     */
    public List<BookType> findMore(){
        session = MyBatisUtil.sqlSessionFactory.openSession();
        BookTypeDAOMapper mapper = session.getMapper(BookTypeDAOMapper.class);
        List<BookType> list = mapper.findMore();
        session.close();
        return list;
    }

    /**
     * 条件查询
     * @param bookType 传入条件
     * @return 返回list
     */
    public List<BookType> findBY(BookType bookType){
        session = MyBatisUtil.sqlSessionFactory.openSession();
        BookTypeDAOMapper mapper = session.getMapper(BookTypeDAOMapper.class);
        List<BookType> list = mapper.findBY(bookType);
        session.close();
        return list;
    }

    /**
     * 根据传入的ID删除一条书籍信息
     * @param tid 传入ID
     * @return 返回boolean
     */
    public boolean deleteID(int tid){
        session = MyBatisUtil.sqlSessionFactory.openSession();
        BookTypeDAOMapper mapper = session.getMapper(BookTypeDAOMapper.class);
        boolean b = mapper.deleteID(tid);
        if (b){
            session.commit();
        }else {
            session.rollback();
        }
        session.close();
        return b;
    }

    /**
     * 根据传入的数据去修改一条信息
     * @param bookType 传入数据
     * @return 返回boolean
     */
    public boolean updateID(BookType bookType){
        session = MyBatisUtil.sqlSessionFactory.openSession();
        BookTypeDAOMapper mapper = session.getMapper(BookTypeDAOMapper.class);
        boolean b = mapper.updateID(bookType);
        if (b){
            session.commit();
        }else {
            session.rollback();
        }
        session.close();
        return b;
    }

    /**
     * 根据传入的数据新增一条书籍信息
     * @param bookType 传入数据
     * @return 返回boolean
     */
    public boolean insertADD(BookType bookType){
        session = MyBatisUtil.sqlSessionFactory.openSession();
        BookTypeDAOMapper mapper = session.getMapper(BookTypeDAOMapper.class);
        boolean b = mapper.insertADD(bookType);
        if (b){
            session.commit();
        }else {
            session.rollback();
        }
        session.close();
        return b;
    }
}
