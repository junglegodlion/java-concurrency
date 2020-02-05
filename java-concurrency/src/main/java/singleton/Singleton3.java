package singleton;

/**
 * 描述：     懒汉式（线程不安全）
 */
public class Singleton3 {

    private static Singleton3 instance;

    private Singleton3() {

    }

    public static Singleton3 getInstance() {
        // 为什么不安全？
        // 因为可能有两个线程同时到达这里
        if (instance == null) {
            instance = new Singleton3();
        }
        return instance;
    }
}
