package threads_01;

// TODO  check if counter value = 2 x 1.000.000
public class RaceConditions_02a {
  public static void main(String[] args) throws InterruptedException {
    class Counter {
      private int counter = 0;

      public void increment() {
        counter++;  // not atomar!
      }

      public int getCounter() {
        return counter;
      }
    }

    final Counter counter = new Counter();

    class CounterThread extends Thread {

      @Override
      public void run() {
        for (int i = 0; i < 1000000; i++) {
          counter.increment();
        }
      }
    }

    CounterThread counterThread1 = new CounterThread();
    CounterThread counterThread2 = new CounterThread();
    counterThread1.start();
    counterThread2.start();
    counterThread1.join();
    counterThread2.join();

    System.out.printf("Counter Value: %d", counter.getCounter());
  }
}
