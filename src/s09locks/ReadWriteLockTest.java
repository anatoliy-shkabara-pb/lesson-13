package s09locks;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {

    static class Reader implements Runnable {

        private final Lock readLock;
        private final StringBuffer buffer;

        public Reader(Lock readLock, StringBuffer buffer) {
            this.readLock = readLock;
            this.buffer = buffer;
        }

        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " try read lock");
            readLock.lock();
            System.out.println(threadName + " read lock");
            try {
                int seconds = new Random().nextInt(10);
                System.out.println(threadName + " read time: " + seconds + " seconds.");
                Thread.sleep(seconds * 1000);
                System.out.println(threadName + " read message: " + buffer.toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                readLock.unlock();
                System.out.println(threadName + " read unlock");
            }
        }
    }

    static class Writer implements Runnable {

        private final Lock writeLock;
        private final StringBuffer buffer;
        private final String str;

        public Writer(Lock writeLock, StringBuffer buffer, String str) {
            this.writeLock = writeLock;
            this.buffer = buffer;
            this.str = str;
        }

        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " try write lock");
            writeLock.lock();
            System.out.println(threadName + " write lock");
            try {
                int seconds = new Random().nextInt(10);
                System.out.println(threadName + " write time " + seconds + " seconds.");
                Thread.sleep(seconds * 1000);
                buffer.append(str);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                writeLock.unlock();
                System.out.println(threadName + " write unlock");
            }
        }
    }

    public static void main(String[] args) {
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        Lock readLock = readWriteLock.readLock();
        Lock writeLock = readWriteLock.writeLock();

        StringBuffer buffer = new StringBuffer("---");

        Thread writerA = new Thread(new Writer(writeLock, buffer, "a"));
        Thread writerB = new Thread(new Writer(writeLock, buffer, "b"));
        Thread writerC = new Thread(new Writer(writeLock, buffer, "c"));
        writerA.setName("WriterA");
        writerB.setName("WriterB");
        writerC.setName("WriterC");

        Thread reader1 = new Thread(new Reader(readLock, buffer));
        Thread reader2 = new Thread(new Reader(readLock, buffer));
        reader1.setName("Reader1");
        reader2.setName("Reader2");

        writerA.start();
        reader1.start();

        writerB.start();
        writerC.start();

        reader2.start();
    }
}
