package threads_01;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ThreadQuizzies {

  // ------------------------------------------------------------------------------

  class MyThread extends Thread{
    private String nameOfThreadThatRuns;

    public MyThread(final String name) {
      super(name);
    }

    public String getNameOfThreadThatRuns() {
      return nameOfThreadThatRuns;
    }


    @Override
    public void run() {
      nameOfThreadThatRuns = Thread.currentThread().getName();
      System.out.println("Running:" + nameOfThreadThatRuns);
    }
  }

  // FIXME
  public MyThread runInThread() throws InterruptedException {
    MyThread myThread = new MyThread("My Thread");
    myThread.run();

    myThread.join(); // wait for end of myThread
    return myThread;
  }

  @Test
  public void shouldHaveNameOfMyThread() throws InterruptedException {
    assertThat(runInThread().getNameOfThreadThatRuns(), is("My Thread"));
  }

  // ------------------------------------------------------------------------------

  public MyThread runThreadAgain() throws InterruptedException {
    MyThread myThread = new MyThread("My Thread");
    myThread.start();
    myThread.join(); // wait for end of myThread

    myThread.start();
    myThread.join();

    return myThread;
  }

  // FIXME
  @Test
  public void runningThreadAgain() throws InterruptedException {
    assertThat(runThreadAgain().getNameOfThreadThatRuns(), is("My Thread"));
  }
}