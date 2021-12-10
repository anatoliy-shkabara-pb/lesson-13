package s01multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class BigTaskManyThreads {

    public Long startTask() {
        int ap = Runtime.getRuntime().availableProcessors();
        System.out.println("Count availableProcessors: " + ap);
        ExecutorService es = Executors.newFixedThreadPool(ap);

        Long summa = 0L;
        try {
            List<MyTask> tasks = new ArrayList<>();
            for (int i = 0; i < Processor.STR_COUNT; i++) {
                tasks.add(new MyTask());
            }
            List<Future<Long>> result = es.invokeAll(tasks);

            for (Future<Long> f : result) {
                summa += f.get();
            }
            es.shutdown();
        } catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace(System.out);
        }
        return summa;
    }
}
