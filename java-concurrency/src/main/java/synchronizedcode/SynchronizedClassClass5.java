package synchronizedcode;

/**
 * 描述: 类锁的第二种形式，synchronized（*.class）
 */
public class SynchronizedClassClass5 implements Runnable {

    static SynchronizedClassClass5 instance1 = new SynchronizedClassClass5();
    static SynchronizedClassClass5 instance2 = new SynchronizedClassClass5();


    @Override
    public void run() {

       method();
    }

    public synchronized void method() {

        // 这里如果是this的话，线程就会并行执行
        synchronized (SynchronizedClassClass5.class) {
            System.out.println("类锁的第二种形式，synchronized（*.class），我叫" +
                    Thread.currentThread().getName());

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() +
                    "运行结束");
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(instance1);
        Thread t2 = new Thread(instance2);
        t1.start();
        t2.start();

        // t1和t2线程存在
        while (t1.isAlive() || t2.isAlive()) {

        }

        System.out.println("finished");
    }
}
