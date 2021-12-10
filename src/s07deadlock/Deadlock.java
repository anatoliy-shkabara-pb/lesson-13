package s07deadlock;

/**
 * Алиса и Боб — друзья и большие приверженцы вежливости.
 * Строгое правило вежливости: когда вы кланяетесь другу вы должны оставаться в поклоне до тех пор,
 * пока ваш друг тоже не поклонится вам.
 * Однако это правило не учитывает возможность, когда оба друга кланяются одновременно.
 *
 * Когда этот пример запустится, то наиболее вероятно, что каждый из потоков будет заблокирован
 * во время попытки вызова bowBack.
 * Ни одна из блокировок никогда не закончится, потому что каждый поток ожидает выхода другого
 * потока из метода bow.
 */
public class Deadlock {

    static class Friend {
        private final String name;

        public Friend(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public synchronized void bow(Friend bower) {
            System.out.format("%s: %s has bowed to me!%n", this.name, bower.getName());
            bower.bowBack(this);
        }

        public synchronized void bowBack(Friend bower) {
            System.out.format("%s: %s has bowed back to me!%n", this.name, bower.getName());
        }
    }

    public static void main(String[] args) {
        Friend alice = new Friend("Alice");
        Friend bob = new Friend("Bob");

        new Thread(() -> alice.bow(bob)).start();
        new Thread(() -> bob.bow(alice)).start();
    }
}
