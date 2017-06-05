package com.xiaofei.hash;

import java.util.stream.IntStream;

/**
 * 类描述
 * <p>
 * 方法描述列表
 * </p>
 * User: xiao Date: 2017/6/1 0001 ProjectName: test
 */
public class HashTest {

    public static Integer DATA_COUNT  = 10000;
    public static String PRE_KEY = "XIAO";

    public static void main(String[] args) {

        narmal();

    }

    private static void narmal() {
        Cluster cluster=new ConsistencyHashCluster();
        cluster.addNode(new Node("c1.yywang.info", "192.168.0.1"));
        cluster.addNode(new Node("c2.yywang.info", "192.168.0.2"));
        cluster.addNode(new Node("c3.yywang.info", "192.168.0.3"));
        cluster.addNode(new Node("c4.yywang.info", "192.168.0.4"));
        IntStream.range(0, DATA_COUNT)
                .forEach(index -> {
                    Node node = cluster.get(PRE_KEY + index);
                    node.put(PRE_KEY + index, "Test Data");
                });
        System.out.println("数据分布情况：");
        cluster.nodes.forEach(node -> {
            System.out.println("IP:" + node.getIp() + ",数据量:" + node.getData().size());
        });
        //增加一个节点 ,测试缓存命中率
        cluster.addNode(new Node("c5.yywang.info", "192.168.0.5"));
        //缓存命中率
        long hitCount = IntStream.range(0, DATA_COUNT)
                .filter(index -> cluster.get(PRE_KEY + index).get(PRE_KEY + index) != null)
                .count();
        System.out.println("缓存命中率：" + hitCount * 1f / DATA_COUNT);
    }
}

