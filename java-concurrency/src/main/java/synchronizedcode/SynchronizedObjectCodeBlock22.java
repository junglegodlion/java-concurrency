package synchronizedcode;

/**
 * 描述: 对象锁示例1，代码块形式
 */
public class SynchronizedObjectCodeBlock22 implements Runnable {

    static SynchronizedObjectCodeBlock22 instance = new SynchronizedObjectCodeBlock22();

    Object lock1 = new Object();
    Object lock2 = new Object();


    @Override
    public void run() {

        // 保证程序是串行的
        synchronized (lock1) {
            System.out.println("我是lock1。我叫"
            + Thread.currentThread().getName());

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName()
            + "lock1部分运行结束");
        }

        synchronized (lock2) {
            System.out.println("我是lock2。我叫"
                    + Thread.currentThread().getName());

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName()
                    + "lock2部分运行结束");
        }
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
