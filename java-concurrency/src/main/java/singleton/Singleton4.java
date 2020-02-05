package singleton;

/**
 * 描述：     懒汉式（线程安全）（不推荐）
 * 缺点：效率太低
 */
public class Singleton4 {

    private static Singleton4 instance;

    private Singleton4() {

    }

    // 拿取实例不可以并行
    public synchronized static Singleton4 getInstance() {
        if (instance == null) {
            instance = new Singleton4();
        }
        return instance;
    }
}
