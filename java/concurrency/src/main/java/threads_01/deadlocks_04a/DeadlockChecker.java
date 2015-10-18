package threads_01.deadlocks_04a;

import java.lang.management.*;
import java.util.*;

/**
 * Taken from http://www.javaspecialists.eu/archive/Issue160.html
 *
 * Used to check whether there are any known deadlocks by
 * querying the ThreadMXBean.  It looks for threads deadlocked
 * on monitors (synchronized) and on Java 5 locks.  Call check()
 * to get a set of deadlocked threads (might be empty).
 *
 * We can also add threads to the ignore set by calling the
 * ignoreThreads(Thread[]) method.  For example, once we have
 * established that a certain deadlock exists, we might want to
 * ignore that until we have shut down our program and instead
 * concentrate on new deadlocks.
 */
public class DeadlockChecker {
  private final static ThreadMXBean tmb =
    ManagementFactory.getThreadMXBean();
  private final Set<Long> threadIdsToIgnore =
    new HashSet<Long>();

  /**
   * Returns set of ThreadInfos that are part of the deadlock;
   * could be empty if there is no deadlock.
   */
  public Collection<ThreadInfo> check() {
    Map<Long, ThreadInfo> map = new HashMap<Long, ThreadInfo>();
    findDeadlockInfo(map, tmb.findMonitorDeadlockedThreads());
    findDeadlockInfo(map, tmb.findDeadlockedThreads());
    return map.values();
  }

  @SuppressWarnings("unused")
  public void ignoreThreads(Thread[] threads) {
    for (Thread thread : threads) {
      threadIdsToIgnore.add(thread.getId());
    }
  }

  private void findDeadlockInfo(Map<Long, ThreadInfo> result,
                                long[] ids) {
    if (ids != null && ids.length > 0) {
      for (long id : ids) {
        if (!threadIdsToIgnore.contains(id)) {
          result.put(id, tmb.getThreadInfo(id));
        }
      }
    }
  }
}
