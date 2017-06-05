package com.xiaofei.string;

import java.util.*;

/**
 * 类描述
 * <p>
 * 方法描述列表
 * </p>
 * User: xiao Date: 2017/4/10 0010 ProjectName: test
 */
public class CompareTest {

    public static void main(String[] args) {
        List list = new ArrayList<Integer>();
        list.add(3);
        list.add(7);
        list.add(0);
        list.add(4);
        Collections.sort(list);

        list.stream().forEach(s-> System.out.println(s));
        int a[] = {4,8,1,5,0};
        Arrays.sort(a);
        Arrays.stream(a).forEach(s-> System.out.println(s));

        People people1 = new People("xiao",21);
        People people2 = new People("zeng",25);
        People people3 = new People("fei",20);
        List<People> list1 = new ArrayList<>();
        list1.add(people1);
        list1.add(people2);
        list1.add(people3);
        Collections.sort(list1, new Comparator<People>() {
            @Override
            public int compare(People o1, People o2) {
                return o2.getAge()-o1.getAge();
            }
        });
       list1.stream().forEach(s-> System.out.println(s));

        Collections.sort(list1);
        list1.stream().forEach(s-> System.out.println(s));

    }

}
