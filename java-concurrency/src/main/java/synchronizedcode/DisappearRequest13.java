package synchronizedcode;


/**
 * 描述： 消失的请求数
 */
public class DisappearRequest13 implements Runnable{

    static DisappearRequest13 instance = new DisappearRequest13();

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
    public  void run() {
        synchronized (this) {
            for (int j = 0; j < 10000; j++) {
                i++;
            }
        }
    }
}
