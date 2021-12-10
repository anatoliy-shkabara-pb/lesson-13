package s01multithreading;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Processor {

    public static final int STR_COUNT = 100;
    public static final int TASK_COUNT = 100000;

    public Long process() {
        long summa = 0;

        SecureRandom random = new SecureRandom();
        for (int i = 0; i < Processor.TASK_COUNT; i++) {
            String g = new BigInteger(500, random).toString(32);
            for (char c : g.toCharArray()) {
                summa += c;
            }
        }
        return summa;
    }
}
