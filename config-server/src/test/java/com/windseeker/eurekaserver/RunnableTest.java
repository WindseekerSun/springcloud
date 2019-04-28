package com.windseeker.eurekaserver;

public class RunnableTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        class MyRunnable implements Runnable {

            private int j = 5;

            @Override
            public void run() {

                synchronized (this) {
                    for (int i = 0; i < 5; i++) {
                        try {
                            Thread.sleep(100);
                            System.out.println(Thread.currentThread().getName() + " loop " + i);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        Runnable runnable = new MyRunnable();
        Thread t1 = new Thread(runnable, "t1");
        Thread t2 = new Thread(runnable, "t2");

        t1.start();
        t2.start();
    }
}
