package s01multithreading;


public class SimpleApp {

    static class ThreadColor extends Thread {

        private final String color;

        public ThreadColor(String color) {
            this.color = color;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println(color + getName() + " iteration: " + (i + 1));
            }
        }
    }


    public static void main(String[] args) {
        Thread green = new ThreadColor(ConsoleColors.ANSI_GREEN);
        Thread blue = new ThreadColor(ConsoleColors.ANSI_BLUE);
        Thread red = new ThreadColor(ConsoleColors.ANSI_RED);

        green.start();
        red.start();
        blue.start();
    }
}
