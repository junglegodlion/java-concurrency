package synchronizedcode;


/**
 * 描述： 消失的请求数
 */
public class DisappearRequest12 implements Runnable{

    static DisappearRequest12 instance = new DisappearRequest12();

    static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);

        t1.start();
        t2.start();

        // 让主线程等待子线程
        t1.join();
        t2.join();
        System.out.println(i);
    }

    @Override
    public synchronized void run() {
        for (int j = 0; j < 10000; j++) {
            i++;
        }
    }
}
