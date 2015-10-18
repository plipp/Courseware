package threads_01.deadlocks_04b.problem;

import threads_01.deadlocks_04b.ProgressListener;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
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

  private synchronized void updateProgress(int n) {
    for (ProgressListener listener : listeners)
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

}
