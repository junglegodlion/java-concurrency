package synchronizedcode;


/**
 * 描述： 消失的请求数
 */
public class DisappearRequest14 implements Runnable{

    static DisappearRequest14 instance = new DisappearRequest14();

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
        synchronized (DisappearRequest14.class) {
            for (int j = 0; j < 10000; j++) {
                i++;
            }
        }
    }
}
