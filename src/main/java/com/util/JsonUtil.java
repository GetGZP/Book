package com.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JsonUtil {

    public static void writeToJSON(Object obj, HttpServletResponse resp) throws IOException{

        JSON.DEFFAULT_DATE_FORMAT="yyyy-MM-dd";
        String str = JSON.toJSONString(obj, SerializerFeature.WriteDateUseDateFormat);
        resp.getWriter().write(str);
    }
}
