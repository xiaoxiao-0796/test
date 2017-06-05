package com.xiaofei.guavacache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * 类描述
 * <p>
 * 方法描述列表
 * </p>
 * User: xiao Date: 2017/3/22 0022 ProjectName: test
 */
public class LocalCache {


        static LoadingCache<String, List> cache = CacheBuilder.newBuilder().refreshAfterWrite(3, TimeUnit.SECONDS)// 给定时间内没有被读/写访问，则回收。
                .expireAfterAccess(3, TimeUnit.SECONDS)// 缓存过期时间和redis缓存时长一样
                .maximumSize(1000).// 设置缓存个数
                build(new CacheLoader<String, List>() {
            @Override
            /** 当本地缓存命没有中时，调用load方法获取结果并将结果缓存 **/
            public List<String> load(String appKey) throws Exception {
                return getAppkeyInfo(appKey);
            }

            /** 数据库进行查询 **/
            private List getAppkeyInfo(String appKey) throws Exception {
                System.out.println("method<getAppkeyInfo> get AppkeyInfo form DB appkey<" + appKey + ">");
                List<String> list = new ArrayList<>();
                list.add("1");
                list.add("2");
                list.add("3");
                list.add("4");
                list.add("5");
                return  list;
            }
        });



        public static List getAppkeyInfoByAppkey(String appKey) throws Exception {
            System.out.println("method<getAppkeyInfoByAppkey> appkey<" + appKey + ">");
            return cache.get(appKey);
        }

    public static void main(String[] args) {
        try {
            List list = getAppkeyInfoByAppkey("1");
            System.out.println(list);
            List list1 = getAppkeyInfoByAppkey("1");
            System.out.println(list1);
//            Thread.sleep(5000);
            List list2 = getAppkeyInfoByAppkey("1");
            System.out.println(list2);
            String xiao = getcallablecache("xiao");
            System.out.println(xiao);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //callable
    static Cache<String, String> cache1 = CacheBuilder.newBuilder().maximumSize(1000).build();

    public static String getcallablecache(String key) throws Exception{
        String resultVal = cache1.get(key, new Callable<String>() {
            public String call() {
                System.out.println("query first...");
                String strProValue = "hello " + key + "!";
                return strProValue;
            }
        });
        return resultVal;
    }

}
