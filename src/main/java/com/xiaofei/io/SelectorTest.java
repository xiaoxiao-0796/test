package com.xiaofei.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 类描述
 * <p>
 * 方法描述列表
 * </p>
 * User: xiao Date: 2017/5/23 0023 ProjectName: test
 */
public class SelectorTest {

    public static void main(String[] args) throws IOException {
        selector1 ();
    }

    private static void selector1() throws IOException {
        //通过调用Selector.open()方法创建一个Selector
        Selector selector = Selector.open ();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking( false );

        ServerSocket ss = ssc.socket();
        InetSocketAddress address = new InetSocketAddress( 9999 );
        ss.bind( address );
        //下一步是将新打开的 ServerSocketChannels 注册到 Selector上
        SelectionKey key1 = ssc.register( selector, SelectionKey.OP_ACCEPT );
        int num = selector.select();

        Set selectedKeys = selector.selectedKeys();
        Iterator it = selectedKeys.iterator();

        while (it.hasNext()) {
            SelectionKey key = (SelectionKey)it.next();
            // ... deal with I/O event ...
            if ((key.readyOps() & SelectionKey.OP_ACCEPT)
                    == SelectionKey.OP_ACCEPT) {
                System.out.println ("OP_ACCEPT");
                // Accept the new connection
                // ...
                ServerSocketChannel ssc1 = (ServerSocketChannel)key.channel();
                SocketChannel sc1 = ssc1.accept();
                sc1.configureBlocking( false );
                SelectionKey newKey = sc1.register( selector, SelectionKey.OP_READ );
                it.remove();
            } else if ((key.readyOps() & SelectionKey.OP_READ)
                    == SelectionKey.OP_READ) {
                // Read the data
                SocketChannel sc = (SocketChannel)key.channel();
                // ...
            }
        }
    }
}
