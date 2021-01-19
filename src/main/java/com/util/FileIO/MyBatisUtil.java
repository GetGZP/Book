package com.util.FileIO;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


import java.io.IOException;
import java.io.InputStream;

/**
 * 获取SqlSessionFactory连接
 */
public class MyBatisUtil {

    private static SqlSessionFactory factory = null;

    static {
        InputStream is = null;

        try {
            is = Resources.getResourceAsStream("mybatis-config.xml");
            Configuration config;
            factory = new SqlSessionFactoryBuilder().build(is);
        } catch (IOException e) {
            throw new RuntimeException("mybatis配置文件加载异常",e);
        }finally {
            if (is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 防止通过new对象的方法获取
     */
    private MyBatisUtil(){}

    /**
     * 获取SqlSession对象
     * @return
     * @throws Exception
     */
    public static SqlSession getSqlSession() throws Exception{
        SqlSession session = factory.openSession();
        return session;
    }

}
