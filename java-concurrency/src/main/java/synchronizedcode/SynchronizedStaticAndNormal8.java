package synchronizedcode;

/**
 * 描述： 同时访问静态synchronized和非静态synchronized方法
 */
public class SynchronizedStaticAndNormal8 implements Runnable {

    static SynchronizedStaticAndNormal8 instance = new SynchronizedStaticAndNormal8();

    @Override
    public void run() {

        if (Thread.currentThread().getName().equals("Thread-0")) {
            method1();
        } else {
            method2();
        }
    }

    // 方法一的锁对象是class
    // 方法二的锁对象是this
    // 两者的锁对象不同，所以可以并发执行
    public synchronized static void method1() {
        System.out.println("我是静态加锁的方法1，我叫" +
                Thread.currentThread().getName());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() +
                "运行结束");
    }

    public synchronized void method2() {
        System.out.println("我是非静态加锁的方法2，我叫" +
                Thread.currentThread().getName());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() +
                "运行结束");
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();

        // t1和t2线程存在
        while (t1.isAlive() || t2.isAlive()) {

        }

        System.out.println("finished");
    }
}

