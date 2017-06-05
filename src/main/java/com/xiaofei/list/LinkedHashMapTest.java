package com.xiaofei.list;

import java.util.LinkedHashMap;
import java.util.Set;

/**
 * 类描述
 * <p>
 * 方法描述列表
 * </p>
 * User: xiao Date: 2017/5/31 0031 ProjectName: test
 */
public class LinkedHashMapTest {

    public static void main(String[] args) {
        LinkedHashMap<String,String> map = new LinkedHashMap<> ();

        map.put ("1","123");
        map.put ("2","321");
        Set<String> strings = map.keySet ();
        for (String s:strings
             ) {
            System.out.println (s);
        }
    }
}
