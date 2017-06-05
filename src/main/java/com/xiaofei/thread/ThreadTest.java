package com.xiaofei.thread;

/**
 * 类描述
 * <p>
 * 方法描述列表
 * </p>
 * User: xiao Date: 2017/6/5 0005 ProjectName: test
 */
public class ThreadTest {

    private static boolean LOCK;

    public static void main(String[] args) {

        Thread thread = new Thread (new Runnable () {
            @Override
            public void run() {
                int i=0;
                while (!LOCK){
                    i++;
                    System.out.println (i);
                }
            }
        });
        thread.start ();
        System.out.println ("斤斤计较");
    }
}
