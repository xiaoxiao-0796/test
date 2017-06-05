package com.xiaofei.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 类描述
 * <p>
 * 方法描述列表
 * </p>
 * User: xiao Date: 2017/5/22 0022 ProjectName: test
 */
public class NIOReadTest {

    public static void main(String[] args) throws Exception {
//        read ();
//        write ();
        readAndWrite();
    }

    private static void readAndWrite() throws Exception {
        FileInputStream fileInputStream = new FileInputStream ("E:\\123.txt");
        FileOutputStream out = new FileOutputStream ("E:\\1234.txt");
        ByteBuffer byteBuffer = ByteBuffer.allocate (128);
        FileChannel fileChannel = fileInputStream.getChannel ();
        FileChannel outChannel = out.getChannel ();
        int read = fileChannel.read (byteBuffer);
        while (read != -1 ){
            byteBuffer.flip ();
            outChannel.write (byteBuffer);
            byteBuffer.clear ();
            read = fileChannel.read (byteBuffer);
        }
        out.close ();
        fileInputStream.close ();
    }

    private static void write() throws IOException {
        FileOutputStream out = new FileOutputStream ("E:\\1234.txt");
        FileChannel channel = out.getChannel ();
        ByteBuffer b1 = ByteBuffer.allocate (1024);
        String s = "helloworld";
        byte[] message = s.getBytes ();
        for (int i=0; i<message.length; ++i) {
            b1.put( message[i] );
        }
        b1.flip();
        channel.write( b1 );
        out.close ();
    }

    private static void read() throws IOException {
        FileInputStream fileInputStream = new FileInputStream ("E:\\123.txt");
        //1.获取通道
        FileChannel channel = fileInputStream.getChannel ();
        ByteBuffer b = ByteBuffer.allocate (1024);
        int read = channel.read (b);
        while (read !=-1){
            System.out.println ("read"+read);
            b.flip ();
            while (b.hasRemaining ()){
                System.out.println ((char)b.get ());
            }
            b.clear ();
            read = channel.read (b);
        }
        fileInputStream.close ();

    }
}

