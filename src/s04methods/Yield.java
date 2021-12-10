package s04methods;

public class Yield {

    static class MyThread extends Thread {
        public void run() {
            for (int i = 0; i < 5; i++)
                System.out.println(Thread.currentThread().getName() + " in control");
        }
    }

    public static void main(String[] args) {
        MyThread t = new MyThread();
        t.start();

        for (int i = 0; i < 5; i++) {
            // Control passes to child thread
            Thread.yield();

            // After execution of child Thread
            // main thread takes over
            System.out.println(Thread.currentThread().getName() + " in control");
        }
    }
}
