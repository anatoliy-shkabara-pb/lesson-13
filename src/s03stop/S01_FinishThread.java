package s03stop;

public class S01_FinishThread {

    //Переменая, которой оперирует инкременатор
    public static int mValue = 0;

    public static void main(String[] args) {
        //Создание потока
        Incremenator mInc = new Incremenator();

        System.out.print("Значение = ");

        //Запуск потока
        mInc.start();

        //Троекратное изменение действия инкременатора
        //с интервалом в i*2 секунд
        for (int i = 1; i <= 3; i++) {
            try {
                //Ожидание в течении i*2 сек.
                Thread.sleep(i * 2 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Переключение действия
            mInc.changeAction();
        }
        //Инициация завершения побочного потока
        mInc.finish();
    }

    static class Incremenator extends Thread {

        private boolean mIsIncrement = true;
        private volatile boolean mFinish = false;

        public synchronized void changeAction() {
            mIsIncrement = !mIsIncrement;
        }

        public void finish() {
            mFinish = true;
        }

        @Override
        public void run() {
            do {
                if (!mFinish) {
                    if (mIsIncrement) {
                        mValue++;
                    } else {
                        mValue--;
                    }
                    //Вывод текущего значения переменной
                    System.out.print(mValue + " ");
                } else {
                    //Завершение потока
                    return;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (true);
        }
    }
}
