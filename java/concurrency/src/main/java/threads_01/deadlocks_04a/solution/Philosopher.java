package threads_01.deadlocks_04a.solution;

public class Philosopher implements Runnable {
  private final Chopstick first;
  private final Chopstick second;

  public Philosopher(Chopstick left, Chopstick right) {
    this.first = left.id < right.id ? left:right;
    this.second = left.id > right.id ? left:right;
  }

  private void ponder() throws InterruptedException {
    Thread.sleep((long) (Math.random() * 10 + 10));
  }

  public void run() {
    try {
      //noinspection InfiniteLoopStatement
      while (true) {
        ponder(); // thinking
        synchronized (first) {
          ponder();
          synchronized (second) {
            ponder(); // eating
          }
        }
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
