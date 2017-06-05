package com.xiaofei.hash;

/**
 * 普通hash算法
 * <p>
 * 方法描述列表
 * </p>
 * User: xiao Date: 2017/6/1 0001 ProjectName: test
 */
public class NormalHashCluster extends Cluster{

    public NormalHashCluster() {
        super();
    }
    @Override
    public void addNode(Node node) {
        this.nodes.add(node);
    }
    @Override
    public void removeNode(Node node) {
        this.nodes.removeIf(o -> o.getIp().equals(node.getIp()) ||
                o.getDomain().equals(node.getDomain()));
    }
    @Override
    public Node get(String key) {
        long hash = (key).hashCode ()<0?key.hashCode ()*(-1) :key.hashCode ();
        long index =  hash % nodes.size();
        return nodes.get((int)index);
    }
}
