package threads_01;

public class Puzzler_03b {
  private static boolean answerReady = false;
  private static int answer = 0;

  private static Thread responder = new Thread("Responder") {
    @Override
    public void run() {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      answer = 42;
      answerReady = true;
    }
  };

  private static Thread requester = new Thread("Requester") {
    @Override
    public void run() {
      while (!answerReady) {

        // TODO: may run forever as only garuanteed to be visible for other threads, if synchronization (join, synchronized...)
        //       happen
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      System.out.println("We have an answer = " + answer);
    }
  };

  public static void main(String[] args) throws InterruptedException {
    responder.start();
    requester.start();

    Thread.sleep(5000);
  }
}
