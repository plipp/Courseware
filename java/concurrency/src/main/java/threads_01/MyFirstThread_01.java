package threads_01;

// TODO
// - execute n-times and see which output comes first
// - comment out yield and execute n-times and see which output comes first
//
// Note:
// use 'yield' only for testing, not in production (not reliable)
public class MyFirstThread_01 {
  public static void main(String[] args) throws InterruptedException {
    Thread thread = new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println("My First NEW Thread is executing");
      }
    });

    thread.start();

    // main-thread is willing to yield its current use of a processor, so that 'thread' can execute:
    // It's just a hint!
    // Same applies to thread-priorities
    // => neither rely on yield() nor on setPriority()
    thread.yield();

    System.out.println("My First MAIN Thread is executing");
    thread.join(); // waits for run to termininate

  }
}
