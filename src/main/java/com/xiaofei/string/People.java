package com.xiaofei.string;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 类描述
 * <p>
 * 方法描述列表
 * </p>
 * User: xiao Date: 2017/4/10 0010 ProjectName: test
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class People implements Comparable<People>{

    private String name;

    private Integer age;

    @Override
    public int compareTo(People o) {
        return this.getAge()-o.getAge();
    }

    public static void main(String[] args) {
        People p = new People();
        p.setAge(12);
        p.setAge(45);
        System.out.println(p);
    }
}
