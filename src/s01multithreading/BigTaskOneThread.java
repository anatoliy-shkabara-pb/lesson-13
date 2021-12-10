package s01multithreading;

public class BigTaskOneThread {

    public Long startTask() {
        Long summa = 0L;
        for (int i = 0; i < Processor.STR_COUNT; i++) {
            Processor p = new Processor();
            summa += p.process();
        }
        return summa;
    }
}
