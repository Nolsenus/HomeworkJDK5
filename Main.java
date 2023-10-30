public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new Philosopher(String.format("Философ %d", i))).start();
        }
    }
}
