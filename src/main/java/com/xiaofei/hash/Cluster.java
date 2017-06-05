package com.xiaofei.hash;

import java.util.ArrayList;
import java.util.List;

/**
 * 集群
 * <p>
 * 方法描述列表
 * </p>
 * User: xiao Date: 2017/6/1 0001 ProjectName: test
 */
public abstract class Cluster {

    protected List<Node> nodes;
    public Cluster() {
        this.nodes = new ArrayList<> ();
    }

    public abstract void addNode(Node node);
    public abstract void removeNode(Node node);
    public abstract Node get(String key);
}
