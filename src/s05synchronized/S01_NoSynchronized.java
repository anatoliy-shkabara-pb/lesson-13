package s05synchronized;

public class S01_NoSynchronized {

    public static void main(String[] args) {
        CommonResource commonResource = new CommonResource();

        for (int i = 1; i < 6; i++) {
            Thread t = new Thread(new CountThread(commonResource));
            t.setName("Thread " + i);
            t.start();
        }
    }

    static class CommonResource {
        int x = 0;
    }

    static class CountThread implements Runnable {
        CommonResource res;

        CountThread(CommonResource res) {
            this.res = res;
        }

        public void run() {
            res.x = 1;
            for (int i = 1; i < 5; i++) {
                System.out.printf("%s %d \n", Thread.currentThread().getName(), res.x);
                res.x++;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
