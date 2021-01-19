package ceshi;

import java.util.Random;

public class MyJsonObject {

    private Object obj;

    public MyJsonObject(Object obj){
        this.obj = obj;
    }

////    public String getJSONString() {
//        Class clazz = obj.getClass();
//        String name = clazz.getName();
//        StringBuilder json = new StringBuilder();
//
////        json.append()
////        return ;
////    }
//
//    @Override
//    public String toString() {
//        return "MyJsonObject{" +
//                "obj=" + obj +
//                '}';
//    }

    public static void main(String[] args) {
        Random random = new Random(System.currentTimeMillis());
        System.out.println(random.nextInt(100000000));
    }
}
