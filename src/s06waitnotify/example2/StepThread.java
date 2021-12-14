package s06waitnotify.example2;

import s01multithreading.ConsoleColors;

/**
 * Программа в которой создаются два потока,
 * которые выводят на консоль своё имя по очереди.
 */
public class StepThread extends Thread {

    // общий для двух потоков lock
    private final Object lock;
    private final String color;

    public StepThread(Object object, String color) {
        this.lock = object;
        this.color = color;
    }

    /**
     * Идея такая: выводим имя потока, потом поток засыпает,
     * перед этим уведомив другой поток, о том, что теперь его очередь.
     * <p>
     * После вызова первым потоком lock.notify() второй поток
     * не просыпается сразу, а ждёт,
     * пока lock не будет освобождён. А когда это происходит, уже вызван
     * метод lock.wait(), и первый поток ждёт своей очереди. И так по кругу.
     */
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            synchronized (lock) {
                try {
                    System.out.println(color + getName() + ConsoleColors.ANSI_RESET);
                    lock.notify();
                    lock.wait(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] strings) {
        Object lock = new Object();
        new StepThread(lock, ConsoleColors.ANSI_BLUE).start();
        new StepThread(lock, ConsoleColors.ANSI_GREEN).start();
    }
}
