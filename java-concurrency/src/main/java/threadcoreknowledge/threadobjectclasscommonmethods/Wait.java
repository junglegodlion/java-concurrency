package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * 描述：     展示wait和notify的基本用法 1. 研究代码执行顺序 2. 证明wait释放锁
 */
public class Wait {

    public static Object object = new Object();

    static class Thread1 extends Thread {

        @Override
        public void run() {
            //synchronized性质：同时最多有一个线程运行
            synchronized (object) {
                System.out.println(Thread.currentThread().getName() + "开始执行了");
                try {
                    // 释放锁
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 线程重新拿到了锁
                System.out.println("线程" + Thread.currentThread().getName() + "获取到了锁。");
            }
        }
    }

    //线程2用来唤醒线程1
    static class Thread2 extends Thread {

        @Override
        public void run() {
            synchronized (object) {
                //唤醒线程1，但不会立马释放锁，
                // 直到执行完程序，即走出synchronized同步代码块，才释放掉锁
                object.notify();
                System.out.println("线程" + Thread.currentThread().getName() + "调用了notify()");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        thread1.start();
        Thread.sleep(200);
        thread2.start();
    }
}
