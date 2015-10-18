package threads_01.deadlocks_04a.solution;

import threads_01.deadlocks_04a.DeadlockChecker;

import java.lang.management.ThreadInfo;
import java.util.Collection;

/**
 * taken from http://www.javaspecialists.eu/archive/Issue160.html
 *
 * sets up a dinner with 5 Philosopers and 5 Chopsticks
 */
public class Dinner {
  public static void main(String[] args) throws Exception {
    final Philosopher[] philosophers = new Philosopher[5];
    Chopstick[] chopsticks = new Chopstick[philosophers.length];
    for (int i = 0; i < chopsticks.length; i++) {
      chopsticks[i] = new Chopstick(i);
    }
    for (int i = 0; i < philosophers.length; i++) {
      Chopstick left = chopsticks[i];
      Chopstick right = chopsticks[(i + 1) % chopsticks.length];
      philosophers[i] = new Philosopher(left, right);
      Thread t = new Thread(philosophers[i], "Phil " + (i + 1));
      t.start();
    }

    DeadlockChecker checker = new DeadlockChecker();
    while (true) {
      Collection<ThreadInfo> threads = checker.check();
      if (!threads.isEmpty()) {
        System.out.println("Found deadlock:");
        for (ThreadInfo thread : threads) {
          System.out.println("\t" + thread.getThreadName());
        }
        System.exit(1);
      }
      Thread.sleep(1000);
    }
  }
}
