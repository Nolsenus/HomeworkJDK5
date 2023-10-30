import java.util.concurrent.locks.ReentrantLock;

public class Philosopher implements Runnable {

    private final String name;
    private int mealsLeftToEat;
    private boolean justAte;
    private static final ReentrantLock lock = new ReentrantLock();

    public Philosopher(String name) {
        this.name = name;
        mealsLeftToEat = 3;
        justAte = false;
    }

    private void think() {
        System.out.printf("%s начал думать.%n", name);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        justAte = false;
        System.out.printf("%s закончил думать.%n", name);
    }

    private void eat() {
        System.out.printf("%s начал обедать.%n", name);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mealsLeftToEat--;
        justAte = true;
        System.out.printf("%s закончил обедать.%n", name);
    }

    @Override
    public void run() {
        while (mealsLeftToEat > 0) {
            if (!justAte && lock.tryLock()) {
                eat();
                lock.unlock();
            } else {
                think();
            }
        }
    }
}
