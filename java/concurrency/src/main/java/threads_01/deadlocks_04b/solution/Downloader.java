package threads_01.deadlocks_04b.solution;

import threads_01.deadlocks_04b.ProgressListener;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Downloader extends Thread {
  private final URL url;
  private final String outputFilename;
  private List<ProgressListener> listeners;

  public Downloader(URL url, String outputFilename) {
    this.url = url;
    this.outputFilename = outputFilename;
    listeners = new ArrayList<ProgressListener>();
  }

  public synchronized void addListener(ProgressListener listener) {
    listeners.add(listener);
  }

  public synchronized void removeListener(ProgressListener listener) {
    listeners.remove(listener);
  }

  private void updateProgress(int n) {
    List<ProgressListener> copy;

    // lock as short as possible (just for copying)
    synchronized (this) {
      copy = (List<ProgressListener>) ((ArrayList<ProgressListener>)listeners).clone();
      Collections.copy(copy, listeners);
    }
    for (ProgressListener listener : copy)
      listener.onProgress(n);
  }


  @Override
  public void run() {
    for (int i = 0; i < 100; i++) {
      try {
        Thread.sleep(2000);
        updateProgress(i);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) throws MalformedURLException {
    final Downloader downloader = new Downloader(new URL("http://www.sec.de"), "test.txt");

    downloader.addListener(new ProgressListener() {
      @Override
      public void onProgress(int n) {
        System.out.println("Progress: " + n);
      }
    });

    // no more deadlocking by progress-listener
    downloader.addListener(new ProgressListener() {
      @Override
      public void onProgress(int n) {
        synchronized (downloader) {
          System.out.println("Got the lock ...");
        }
      }
    });

    downloader.start();
  }
}
