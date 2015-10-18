package threads_01;

import java.util.concurrent.atomic.AtomicInteger;

public class RaceConditions_02c {
  public static void main(String[] args) throws InterruptedException {
    class Counter {
      private AtomicInteger counter = new AtomicInteger(0);

      public void increment() {
        counter.incrementAndGet();
      }

      public int getCounter() {
        return counter.get();
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
