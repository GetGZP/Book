package com.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


import java.io.IOException;
import java.io.InputStream;

public class MyBatisUtil {
    public static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            //写入MyBatis核心配置文件名称
            String resource = "mybatis-config.xml";
            InputStream   inputstream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputstream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
