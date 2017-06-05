package com.xiaofei.hash;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 节点
 * <p>
 * 方法描述列表
 * </p>
 * User: xiao Date: 2017/6/1 0001 ProjectName: test
 */
@Data
public class Node{

        private String domain;
        private String ip;
        private Map<String, Object> data = new HashMap<> ();
        public Node(){};
        public Node(String domain,String ip){
            this.domain = domain;
            this.ip = ip;
        };
        public <T> void put(String key, T value) {
            data.put(key, value);
        }
        public void remove(String key){
            data.remove(key);
        }
        public <T> T get(String key) {
            return (T) data.get(key);
        }
}
