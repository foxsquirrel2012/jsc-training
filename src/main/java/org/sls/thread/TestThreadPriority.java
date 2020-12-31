package org.sls.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class TestThreadPriority {
    // 定义max\nor\min 优先级（默认值：10,5,1），方便修改；
    static final int MAX_PRI = Thread.MAX_PRIORITY;
    static final int NOR_PRI = Thread.NORM_PRIORITY;
    static final int MIN_PRI = Thread.MIN_PRIORITY;

    static AtomicLong minTimes = new AtomicLong(0);
    static AtomicLong normTimes = new AtomicLong(0);
    static AtomicLong maxTimes = new AtomicLong(0);

    public static void main(String[] args) {
        List<MyThread> minThreadList = new ArrayList<>();
        List<MyThread> normThreadList = new ArrayList<>();
        List<MyThread> maxThreadList = new ArrayList<>();

        int count = 1000;
        for (int i = 0; i < count; i++) {
            MyThread myThread = new MyThread("min----" + i);
            myThread.setPriority(MIN_PRI);
            minThreadList.add(myThread);
        }
        for (int i = 0; i < count; i++) {
            MyThread myThread = new MyThread("norm---" + i);
            myThread.setPriority(NOR_PRI);
            normThreadList.add(myThread);
        }
        for (int i = 0; i < count; i++) {
            MyThread myThread = new MyThread("max----" + i);
            myThread.setPriority(MAX_PRI);
            maxThreadList.add(myThread);
        }

        for (int i = 0; i < count; i++) {
            maxThreadList.get(i).start();
            normThreadList.get(i).start();
            minThreadList.get(i).start();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("maxPriority  统计：" + maxTimes.get());
        System.out.println("normPriority 统计：" + normTimes.get());
        System.out.println("minPriority  统计：" + minTimes.get());
        System.out.println("普通优先级与最高优先级相差时间：" + (normTimes.get() - maxTimes.get()) + "ms");
        System.out.println("最低优先级与普通优先级相差时间：" + (minTimes.get() - normTimes.get()) + "ms");

    }

    static class MyThread extends Thread {

        public MyThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            System.out.println(this.getName() + " priority: " + this.getPriority());
            switch (this.getPriority()) {
                case MAX_PRI:
                    maxTimes.getAndAdd(System.currentTimeMillis());
                    break;
                case NOR_PRI:
                    normTimes.getAndAdd(System.currentTimeMillis());
                    break;
                case MIN_PRI:
                    minTimes.getAndAdd(System.currentTimeMillis());
                    break;
                default:
                    break;
            }
        }
    }
}
