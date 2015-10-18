package threads_01;

// TODO
// Do we always expect the output: We have an answer = 42 ???
public class Puzzler_03a {
  private static boolean answerReady = false;
  private static int answer = 0;

  private static Thread responder = new Thread("Responder") {
    @Override
    public void run() {
      answer = 42;
      answerReady = true;
    }
  };

  private static Thread requester = new Thread("Requester") {
    @Override
    public void run() {
      if (answerReady) {
        System.out.println("We have an answer = " + answer);
      } else {
        System.out.println("Don't know");
      }
    }
  };

  public static void main(String[] args) throws InterruptedException {
    responder.start();
    requester.start();

    requester.join();
    responder.join();
  }
}
