import scala.concurrent.Future
import scala.util.{Success, Try}

// ------------------------------------------------------------------
// Try vs. recover
def doSomethingDangerous (i:Int): Future[Int] = {
  if (i%2==0) throw new IllegalArgumentException
  else Future.failed(new IllegalArgumentException)
}

val value2: Int = 45

val triedDangerousOperation2: Try[Future[Int]] = Try {
  doSomethingDangerous(value2)
}

val future: Future[Int] = triedDangerousOperation2 match {
  case Success(eventualInt) => println(s"It works: $eventualInt"); eventualInt
  case _ => println("No"); Future.successful(-1)
}

// TODO decomment
// Await.result(future, Duration(2, TimeUnit.SECONDS)) // bang!!!
// Await.result(future.recover({case _ => -1}), Duration(2, TimeUnit.SECONDS)) // bang!!!

