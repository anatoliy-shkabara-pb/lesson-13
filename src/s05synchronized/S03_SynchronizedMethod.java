package s05synchronized;

public class S03_SynchronizedMethod {

    public static void main(String[] args) {
        CommonResource commonResource = new CommonResource();
        for (int i = 1; i < 6; i++) {

            Thread t = new Thread(new CountThread(commonResource));
            t.setName("Thread " + i);
            t.start();
        }
    }

    static class CommonResource {
        int x;

        synchronized void increment() {
            x = 1;
            for (int i = 1; i < 5; i++) {
                System.out.printf("%s %d \n", Thread.currentThread().getName(), x);
                x++;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class CountThread implements Runnable {
        CommonResource res;

        CountThread(CommonResource res) {
            this.res = res;
        }

        public void run() {
            res.increment();
        }
    }
}
