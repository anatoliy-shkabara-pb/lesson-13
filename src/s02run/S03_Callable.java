package s02run;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class S03_Callable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Callable<Integer> callable = () -> {
            System.out.println("Привет из побочного потока!");
            return 2 * 15;
        };

        FutureTask<Integer> task = new FutureTask<>(callable);
        Thread t = new Thread(task);
        t.start();
        System.out.println("Результат работы побочного потока: " + task.get());
        System.out.println("Главный поток завершён...");
    }
}
