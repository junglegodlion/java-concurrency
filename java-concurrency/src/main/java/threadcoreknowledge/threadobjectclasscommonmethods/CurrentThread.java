package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * 描述：     演示打印main, Thread-0, Thread-1
 */
public class CurrentThread implements Runnable {

    public static void main(String[] args) {
        //让主线程调用run方法
        new CurrentThread().run();

        //让子线程调用run方法
        new Thread(new CurrentThread()).start();
        new Thread(new CurrentThread()).start();
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
