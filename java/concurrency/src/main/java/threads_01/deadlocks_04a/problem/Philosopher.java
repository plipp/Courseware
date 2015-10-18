package threads_01.deadlocks_04a.problem;

public class Philosopher implements Runnable {
  private final Chopstick left;
  private final Chopstick right;

  public Philosopher(Chopstick left, Chopstick right) {
    this.left = left;
    this.right = right;
  }

  private void ponder() throws InterruptedException {
    Thread.sleep((long) (Math.random() * 10 + 10));
  }

  public void run() {
    try {
      //noinspection InfiniteLoopStatement
      while (true) {
        ponder(); // thinking
        synchronized (left) {
          ponder();
          synchronized (right) {
            ponder(); // eating
          }
        }
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
