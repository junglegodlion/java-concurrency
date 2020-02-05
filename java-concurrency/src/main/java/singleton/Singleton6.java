package singleton;

/**
 * 描述：     双重检查（推荐面试使用）
 */
public class Singleton6 {

    private volatile static Singleton6 instance;

    private Singleton6() {

    }

    public static Singleton6 getInstance() {
        if (instance == null) {
            synchronized (Singleton6.class) {
                // 不加volatile。不能保证可见性
                // 线程2就看不到线程1创建实例
                if (instance == null) {
                    instance = new Singleton6();
                }
            }
        }
        return instance;
    }
}
