package com.xiaofei.hash;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.IntStream;

/**
 * 一致性hash算法
 * <p>
 * 方法描述列表
 * </p>
 * User: xiao Date: 2017/6/1 0001 ProjectName: test
 */
public class ConsistencyHashCluster extends Cluster {

    private SortedMap<Long, Node> virNodes = new TreeMap<Long, Node> ();
    private static final int VIR_NODE_COUNT = 10000;
    private static final String SPLIT = "#";
    public ConsistencyHashCluster() {
        super();
    }
    @Override
    public void addNode(Node node) {
        this.nodes.add(node);
        IntStream.range(0, VIR_NODE_COUNT)
                .forEach(index -> {
                    long hash = hash (node.getIp() + SPLIT + index);
                    virNodes.put(hash, node);
                });
    }
    @Override
    public void removeNode(Node node) {
        nodes.removeIf(o -> node.getIp().equals(o.getIp()));
        IntStream.range(0, VIR_NODE_COUNT)
                .forEach(index -> {
                    long hash =hash (node.getIp() + SPLIT + index);
                    virNodes.remove(hash);
                });
    }
    @Override
    public Node get(String key) {
        long hash = hash (key);
        SortedMap<Long, Node> subMap = hash >= virNodes.lastKey() ?
                virNodes.tailMap(0L) : virNodes.tailMap(hash);
        if (subMap.isEmpty()) {
            return null;
        }
        return subMap.get(subMap.firstKey());
    }

    /**
     * MurMurHash算法，是非加密HASH算法，性能很高，
     * 比传统的CRC32,MD5，SHA-1（这两个算法都是加密HASH算法，复杂度本身就很高，带来的性能上的损害也不可避免）
     * 等HASH算法要快很多，而且据说这个算法的碰撞率很低. http://murmurhash.googlepages.com/
     */
    private Long hash(String key) {
        ByteBuffer buf = ByteBuffer.wrap(key.getBytes());
        int seed = 0x1234ABCD;

        ByteOrder byteOrder = buf.order();
        buf.order(ByteOrder.LITTLE_ENDIAN);

        long m = 0xc6a4a7935bd1e995L;
        int r = 47;

        long h = seed ^ (buf.remaining() * m);

        long k;
        while (buf.remaining() >= 8) {
            k = buf.getLong();

            k *= m;
            k ^= k >>> r;
            k *= m;

            h ^= k;
            h *= m;
        }

        if (buf.remaining() > 0) {
            ByteBuffer finish = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN);
            // for big-endian version, do this first:
            // finish.position(8-buf.remaining());
            finish.put(buf).rewind();
            h ^= finish.getLong();
            h *= m;
        }

        h ^= h >>> r;
        h *= m;
        h ^= h >>> r;

        buf.order(byteOrder);
        return h;
    }
}
