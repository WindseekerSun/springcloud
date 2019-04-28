package com.windseeker.eurekaserver;

public class WaitTest {

    /**
     * @param args
     */
    public static void main(String[] args) {


        ThreadA t1 = new ThreadA("t1");

        synchronized (t1) {
            //启动线程t1
            System.out.println(Thread.currentThread().getName() + " start t1");
            t1.start();

            try {
                //主线程等待t1通过 notify（）唤醒。
                System.out.println(Thread.currentThread().getName() + " wait()");
                t1.wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " continue");

        }
    }

    static class ThreadA extends Thread {

        public ThreadA(String name) {
            super(name);
        }

        @Override
        public void run() {
            synchronized (this) {
                System.out.println(Thread.currentThread().getName() + " 正在执行");

                System.out.println(Thread.currentThread().getName() + " 现在要执行， call notify()");
                notify();
            }

        }
    }

}
