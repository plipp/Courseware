package threads_01;

public class RaceConditions_02b {
  public static void main(String[] args) throws InterruptedException {
    class Counter {
      private int counter = 0;

      // synchronized aka intrinsic lock
      synchronized public void increment() {
        counter++;
      }

      // and the same with synchronized block
//      public void increment() {
//        synchronized (this) {
//          counter++;
//        }
//      }

      // should be synchronized because of reasons listed in Puzzler_03b as well!
      synchronized public int getCounter() {
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
