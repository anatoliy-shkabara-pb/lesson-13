package s03stop;

@SuppressWarnings("Duplicates")
public class S02_Interruption {

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

        //Прерывание побочного потока
        mInc.interrupt();
    }

    static class Incremenator extends Thread {

        private boolean mIsIncrement = true;

        public synchronized void changeAction() {
            mIsIncrement = !mIsIncrement;
        }

        @Override
        public void run() {
            while (true) {
                //Проверка прерывания
                if (!Thread.interrupted()) {
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
                    //Приостановка потока на 1 сек.
                    Thread.sleep(100000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
            }
        }
    }
}
