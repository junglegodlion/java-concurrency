package synchronizedcode;

/**
 * 描述： 可重入粒度测试,调用父类的方法
 */
public class SynchronizedSuperClass12 {

    public synchronized void doSomething() {
        System.out.println("我是父类方法");
    }


}

class TestClass extends SynchronizedSuperClass12 {
    public synchronized void doSomething() {
        System.out.println("我是子类方法");

        // 调用父类方法
        super.doSomething();
    }

    public static void main(String[] args) {
        TestClass s = new TestClass();
        s.doSomething();
    }
}
