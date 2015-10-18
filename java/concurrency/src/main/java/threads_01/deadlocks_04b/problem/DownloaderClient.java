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

        // deadlock by progress-listeners
        int i=1;
        downloader.addListener(new SyncedProgressListener(downloader,i++));
        downloader.addListener(new SyncedProgressListener(downloader,i++));
        downloader.addListener(new SyncedProgressListener(downloader,i++));
        downloader.addListener(new SyncedProgressListener(downloader,i++));
        downloader.addListener(new SyncedProgressListener(downloader,i++));
        downloader.addListener(new SyncedProgressListener(downloader,i++));
        downloader.addListener(new SyncedProgressListener(downloader,i++));
        downloader.addListener(new SyncedProgressListener(downloader,i++));
        downloader.addListener(new SyncedProgressListener(downloader,i++));

        // start the downloader thread
        downloader.start();
    }

    static class SyncedProgressListener implements ProgressListener {
        private Downloader downloader;
        private final int no;

        SyncedProgressListener(Downloader downloader, int no) {
            this.downloader = downloader;
            this.no = no;
        }

        @Override
        public void onProgress(int n) {
            new Thread() {
                @Override
                public void run() {
                    synchronized (downloader) {
                        System.out.println(no +": Got the lock ...");
//                        tryToSleep(1000);
                        downloader.removeListener(SyncedProgressListener.this);
                        System.out.println(no + ": finished");
                    }
                }
            }.start();
        }
    }

    private static void tryToSleep(int durationInMillis) {
        try {
            Thread.sleep(durationInMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
