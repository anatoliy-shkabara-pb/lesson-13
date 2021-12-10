package s02run;

public class S01_Runnable {

    public static void main(String[] args) {
        SomeThing mThing = new SomeThing();

        Thread myThready = new Thread(mThing);
        myThready.start();

        System.out.println("Главный поток завершён...");
    }


    static class SomeThing implements Runnable {
        public void run() {
            System.out.println("Привет из побочного потока!");
        }
    }
}
