package threads_01.deadlocks_04b.problem;

import threads_01.deadlocks_04b.ProgressListener;

import java.net.MalformedURLException;
import java.net.URL;

public class DownloaderClient {
  public static void main(String[] args) throws MalformedURLException {
    final Downloader downloader = new Downloader(new URL("http://www.sec.de"), "test.txt");

    downloader.addListener(new ProgressListener() {
      @Override
      public void onProgress(int n) {
        System.out.println("Progress: " + n);
      }
    });

    // deadlock by progress-listener
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
