package s01multithreading;

import java.util.concurrent.Callable;

public class MyTask implements Callable<Long> {
    @Override
    public Long call() throws Exception {
        Processor p = new Processor();
        return p.process();
    }
}