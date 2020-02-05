package synchronizedcode;

/**
 * 描述： 可重入粒度测试:调用类内另外的方法
 */
public class SynchronizedOtherMethod11 {

    public static void main(String[] args) {
        SynchronizedOtherMethod11 synchronizedRecursion10 = new SynchronizedOtherMethod11();
        synchronizedRecursion10.method1();
    }

    private synchronized void method1() {
        System.out.println("这是method1");
        method2();

    }

    private synchronized void method2() {
        System.out.println("这是method2");
    }
}
