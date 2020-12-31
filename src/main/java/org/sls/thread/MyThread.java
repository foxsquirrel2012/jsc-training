package org.sls.thread;

public class MyThread extends Thread {
    public MyThread(String threadName) {
        super(threadName);
    }

    public static void main(String[] args) {
        Thread.currentThread().setPriority(8);
        System.out.println(Thread.currentThread().getPriority());
        Thread a = new MyThread("A");
        Thread b = new MyThread("B");
        a.setPriority(Thread.MIN_PRIORITY);
        b.setPriority(Thread.MAX_PRIORITY);
        System.out.println(a.getPriority());
        System.out.println(b.getPriority());

        b.start();
        a.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(String.format("%s:%s", Thread.currentThread().getName(), i));
        }
    }
}
