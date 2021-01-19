package com.service;

import com.dao.BookDAOMapper;
import com.entity.Book;
import com.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class MainHomePageService {

      private static SqlSession session;

    /**
     * 根据传入的tid查询相应的书籍
     * @param book 传入book
     * @return 返回Boolean
     */
    public List<Book> findMore(Book book){
            session = MyBatisUtil.sqlSessionFactory.openSession();
            BookDAOMapper mapper = session.getMapper(BookDAOMapper.class);
            List<Book> list = mapper.findMore(book);
            session.commit();
            session.close();
            return list;
      }
}
