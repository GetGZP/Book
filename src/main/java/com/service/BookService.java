package com.service;

import com.dao.BookDAOMapper;
import com.entity.Book;
import com.entity.BookType;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class BookService {

    private static SqlSession session;


    /**
     * 带分页的全部查询,或者模糊查询
     * @param book 传入要查询的信息
     * @param pageNum 传入当前是第几页
     * @param pageSize 传入每页要显示几条数据
     * @return 返回带分页数据的集合
     */
    public PageInfo<Book> findMore(Book book,int pageNum,int pageSize){
        session = MyBatisUtil.sqlSessionFactory.openSession();
        BookDAOMapper mapper = session.getMapper(BookDAOMapper.class);
        if(book !=null && book.getBname()!=null){
            book.setBname("%"+book.getBname()+"%");
        }else if(book == null){
            book = new Book();
        }
        if(book.getBookType() ==null){
            book.setBookType(new BookType());
        }
        if(pageNum==0){
            pageNum=1;
        }
        if(pageSize==0){
            pageSize=2;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<Book> list = mapper.findMore(book);

        if(book.getBid()!=0){
            List<BookType> more = new BookTypeService().findMore();
            list.get(0).setBookTypeList(more);
        }

        PageInfo<Book> pageInfo = new PageInfo<Book>(list);
        session.close();
        return pageInfo;
    }

    /**
     * 根据传入的数据修改一条数据
     * @param book 传入参数
     * @return 返回boolean
     */
    public boolean updateID(Book book){
        session = MyBatisUtil.sqlSessionFactory.openSession();
        BookDAOMapper mapper = session.getMapper(BookDAOMapper.class);
        boolean b = mapper.updateID(book);
        if(b){
            session.commit();
        }else {
            session.rollback();
        }
        session.close();
        return b;
    }

    /**
     * 根据传入的数据去修改书籍状态
     * @param book 传入状态和ID
     * @return 返回boolean
     */
    public boolean upbstate(Book book){
        session = MyBatisUtil.sqlSessionFactory.openSession();
        BookDAOMapper mapper = session.getMapper(BookDAOMapper.class);
        boolean b = mapper.upbstate(book);
        if(b){
            session.commit();
        }else {
            session.rollback();
        }
        session.close();
        return b;
    }

    /**
     * 新增一条数据
     * @param book 传入数据
     * @return 返回Boolean
     */
    public boolean insertADD(Book book){
        session = MyBatisUtil.sqlSessionFactory.openSession();
        BookDAOMapper mapper = session.getMapper(BookDAOMapper.class);
        boolean b = mapper.insertADD(book);
        if(b){
            session.commit();
        }else {
            session.rollback();
        }
        session.close();
        return b;
    }

    /**
     * 根据传入的数据批量修改书籍状态
     * @param bstate 传入状态
     * @param bid 传入ID
     * @return 返回Boolean
     */
    public boolean stateupdates(String bstate,String ...bid){
        session = MyBatisUtil.sqlSessionFactory.openSession();
        BookDAOMapper mapper = session.getMapper(BookDAOMapper.class);
        boolean b = mapper.stateupdates(bstate, bid);
        if(b){
            session.commit();
        }else {
            session.rollback();
        }
        session.close();
        return b;
    }
}
