package com.service;

import com.dao.BookDAOMapper;
import com.entity.Book;
import com.entity.BookType;
import com.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class BookShowService {

    private static SqlSession session;

    /**
     查询全部商品或者根据类型查询以及是否状态正常
     * @param book 传入类型
     * @return 返回List
     */
    public List<Book> findMore(Book book){
        if(book == null){
            book = new Book();
            BookType bookType = new BookType();
            book.setBookType(bookType);
        }

        session = MyBatisUtil.sqlSessionFactory.openSession();
        BookDAOMapper mapper = session.getMapper(BookDAOMapper.class);
        List<Book> list = mapper.findMore(book);
        session.close();
        return list;
    }

    /**
     * 查询热卖商品不包含已下架
     * @return 返回list
     */
    public List<Book> findegroup(){
        session = MyBatisUtil.sqlSessionFactory.openSession();
        BookDAOMapper mapper = session.getMapper(BookDAOMapper.class);
        List<Book> list = mapper.findegroup();
        session.close();
        return list;
    }


}
