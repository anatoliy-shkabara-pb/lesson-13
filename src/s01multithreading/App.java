package s01multithreading;

public class App {

    public static void main(String[] args) {
        runOneThreadTask();
        runManyThreadTask();
    }

    static void runOneThreadTask() {
        System.out.println("Start one tread task");
        BigTaskOneThread bt = new BigTaskOneThread();
        long d1 = System.currentTimeMillis();
        Long result = bt.startTask();
        long d2 = System.currentTimeMillis();
        System.out.println(result + ", Time: " + (d2 - d1));
    }

    static void runManyThreadTask() {
        System.out.println("Start many treads task");
        BigTaskManyThreads bt = new BigTaskManyThreads();
        long d1 = System.currentTimeMillis();
        Long result = bt.startTask();
        long d2 = System.currentTimeMillis();
        System.out.println(result + ", Time: " + (d2 - d1));
    }
}
