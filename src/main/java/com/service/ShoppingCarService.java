package com.service;


import com.dao.ShoppingCarDAOMapper;
import com.entity.Book;
import com.entity.ShoppingCar;
import com.entity.User;
import com.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;


import java.util.List;

public class ShoppingCarService {

    private static SqlSession session;

    /**
     * 查询
     *
     * @param shoppingCar
     * @return
     */
    public List<ShoppingCar> findMore(ShoppingCar shoppingCar) {
        SqlSession session = MyBatisUtil.sqlSessionFactory.openSession();
        ShoppingCarDAOMapper mapper = session.getMapper(ShoppingCarDAOMapper.class);
        if (shoppingCar == null) {
            Book book = new Book();
            User user = new User();
            shoppingCar = new ShoppingCar();
            shoppingCar.setUser(user);
            shoppingCar.setBook(book);
        } else if (shoppingCar.getBook() == null) {
            Book book = new Book();
            shoppingCar.setBook(book);
        } else if (shoppingCar.getUser() == null) {
            User user = new User();
            shoppingCar.setUser(user);
        }
        List<ShoppingCar> list = mapper.findMore(shoppingCar);
        session.commit();
        session.close();
        return list;
    }

    /**
     * 查询新增一条
     * @param shoppingCar
     * @return
     */
    public Integer ADDCar1(ShoppingCar shoppingCar){
        boolean c = false;
        SqlSession session = MyBatisUtil.sqlSessionFactory.openSession();
        ShoppingCarDAOMapper mapper = session.getMapper(ShoppingCarDAOMapper.class);
        shoppingCar.setSnumber(1);
        shoppingCar.setStotalprice(shoppingCar.getBook().getBprice());
        c = mapper.ADDCar(shoppingCar);
        Integer sid = mapper.ADD();
        if (c){
            session.commit();
        }else {
            session.rollback();
        }
        session.close();
        return sid;
    }

    /**
     * 新增一条数据
     * @param shoppingCar
     * @return
     */
    public boolean ADDCar(ShoppingCar shoppingCar){
        SqlSession session = MyBatisUtil.sqlSessionFactory.openSession();
        ShoppingCarDAOMapper mapper = session.getMapper(ShoppingCarDAOMapper.class);
        //先去查询有没有此数据如果没有size为0
        List<ShoppingCar> list = mapper.findMore(shoppingCar);
        boolean c = false;
        if (list.size()==0){
            //如果返回集合为0,则调用新增方法,增加一条数据
            shoppingCar.setSnumber(1);
            shoppingCar.setStotalprice(shoppingCar.getBook().getBprice());
            c = mapper.ADDCar(shoppingCar);
        }else {
            //如果不为0则修改数量和总价在调用修改方法
            ShoppingCar car = list.get(0);
            double bprice = car.getBook().getBprice();
            int snumber = car.getSnumber();
            double stotalprice = car.getStotalprice();
            car.setSnumber(snumber + 1);
            car.setStotalprice(stotalprice + bprice);
            c = mapper.updateCar(car);
        }
        if(c){
            session.commit();
        }else {
            session.rollback();
        }
        session.close();
        return c;
    }

    /**
     * 数据进行增减操作
     * @param shoppingCar
     * @param panDing
     * @return
     */
    public List<ShoppingCar> findPanDing(ShoppingCar shoppingCar,String panDing){
        SqlSession session = MyBatisUtil.sqlSessionFactory.openSession();
        ShoppingCarDAOMapper mapper = session.getMapper(ShoppingCarDAOMapper.class);
        //先查询数据
        List<ShoppingCar> list = mapper.findMore(shoppingCar);
        boolean c = false;
        if(panDing.equals("jia")){
            ShoppingCar car = list.get(0);
            double bprice = car.getBook().getBprice();
            int snumber = car.getSnumber();
            double stotalprice = car.getStotalprice();
            car.setSnumber(snumber + 1);
            car.setStotalprice(stotalprice + bprice);
            c = mapper.updateCar(car);

        }else if (panDing.equals("jian")){
            ShoppingCar car = list.get(0);
            double bprice = car.getBook().getBprice();
            int snumber = car.getSnumber();
            double stotalprice = car.getStotalprice();
            car.setSnumber(snumber - 1);
            car.setStotalprice(stotalprice - bprice);
            c = mapper.updateCar(car);

        }else if (panDing.equals("put")){
            ShoppingCar car = list.get(0);
            double bprice = car.getBook().getBprice();
            car.setStotalprice(shoppingCar.getSnumber() * bprice);
            car.setSnumber(shoppingCar.getSnumber());
            c = mapper.updateCar(car);
        }
        List<ShoppingCar> more = mapper.findMore(shoppingCar);
        if(c){
            session.commit();
        }else {
            session.rollback();
        }
        session.close();
        return more;
    }

    /**
     * 查询购物车中有几件商品
     * @param uid
     * @return
     */
    public int findNumber(int uid){
        SqlSession session = MyBatisUtil.sqlSessionFactory.openSession();
        ShoppingCarDAOMapper mapper = session.getMapper(ShoppingCarDAOMapper.class);
        Integer number = mapper.findNumber(uid);
        session.commit();
        session.close();
        if(number == null){
            number = 0;
        }
        return number;
    }

    /**
     * 批量查询
     * @param sid
     * @return
     */
    public  List<ShoppingCar> findBySid(String ...sid){
        SqlSession session = MyBatisUtil.sqlSessionFactory.openSession();
        ShoppingCarDAOMapper mapper = session.getMapper(ShoppingCarDAOMapper.class);
        List<ShoppingCar> list = mapper.findBySid(sid);
        session.commit();
        session.close();
        return list;
    }

    /**
     * 根据传入的参数删除一条数据
     * @param shoppingCar
     * @return
     */
    public boolean deleteCar(ShoppingCar shoppingCar){
        SqlSession session = MyBatisUtil.sqlSessionFactory.openSession();
        ShoppingCarDAOMapper mapper = session.getMapper(ShoppingCarDAOMapper.class);
        boolean b = mapper.deleteCar(shoppingCar);
        if(b){
            session.commit();
        }else {
            session.rollback();
        }
        session.close();
        return b;
    }

    /**
     * 批量删除
     * @param sid
     * @return
     */
    public boolean deleteCares(String ...sid){
        SqlSession session = MyBatisUtil.sqlSessionFactory.openSession();
        ShoppingCarDAOMapper mapper = session.getMapper(ShoppingCarDAOMapper.class);
        boolean b = mapper.deleteCares(sid);
        if(b){
            session.commit();
        }else {
            session.rollback();
        }
        session.close();
        return b;
    }

    /**
     * 根据传入的参数进行修改
     * @param shoppingCar
     * @return
     */
    public boolean updateCar(ShoppingCar shoppingCar){
        SqlSession session = MyBatisUtil.sqlSessionFactory.openSession();
        ShoppingCarDAOMapper mapper = session.getMapper(ShoppingCarDAOMapper.class);
        boolean b = mapper.updateCar(shoppingCar);
        if(b){
            session.commit();
        }else {
            session.rollback();
        }
        session.close();
        return b;
    }
}
