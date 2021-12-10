package s02run;

import java.util.Random;

public class S04_Example {

    static Random rand = new Random();

    static class EggVoice extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                try {
                    //Приостанавливает поток на 1 секунду максимум
                    sleep(rand.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("яйцо!");
            }
            //Слово «яйцо» сказано 5 раз
        }
    }


    public static void main(String[] args) {
        // Создание потока
        EggVoice mAnotherOpinion = new EggVoice();
        System.out.println("Спор начат...");

        // Запуск потока
        mAnotherOpinion.start();

        for (int i = 0; i < 5; i++) {
            try {
                // Приостанавливает поток на 1 секунду максимум
                Thread.sleep(rand.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("курица!");
        }

        // Слово «курица» сказано 5 раз
        // Если оппонент еще не сказал последнее слово
        if (mAnotherOpinion.isAlive()) {
            try {
                // Подождать пока оппонент закончит высказываться.
                mAnotherOpinion.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Первым появилось яйцо!");

        } else {
            //если оппонент уже закончил высказываться
            System.out.println("Первой появилась курица!");
        }
        System.out.println("Спор закончен!");
    }
}
