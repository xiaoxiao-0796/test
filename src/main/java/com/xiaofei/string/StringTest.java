package com.xiaofei.string;

import java.lang.reflect.Field;

/**
 * 类描述
 * <p>
 *通过反射机制改变String实例
 * 方法描述列表
 * </p>
 * User: xiao Date: 2017/3/22 0022 ProjectName: test
 */
public class StringTest {
    public static void main(String[] args) {
        String s =  "123456";
        System.out.println("s="+s);
        try {
            Field value = String.class.getDeclaredField("value");
            value.setAccessible(true);//修改访问权限
            char[] c = (char[])value.get(s);
            c[0]='8';
            System.out.println("s="+s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
