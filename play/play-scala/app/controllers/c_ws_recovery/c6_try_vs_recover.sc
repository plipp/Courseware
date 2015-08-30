import scala.concurrent.ExecutionContext.Implicits.global
import java.util.concurrent.TimeUnit
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.util.{Success, Try}

// ------------------------------------------------------------------
// Try
def doSomethingDangerous (i:Int): Int = {
  if (i%2==0) throw new IllegalArgumentException
  else i*99
}

val value: Int = 45

val triedDangerousOperation: Try[Int] = Try {doSomethingDangerous(value)}

val intValue: Int = triedDangerousOperation match {
  case Success(i) => println(s"It works: $i"); i
  case _ => println("No"); -1
}
intValue

// ------------------------------------------------------------------
// Try vs. recover
def doSomethingDangerous2 (i:Int): Future[Int] = {
  if (i%2==0) throw new IllegalArgumentException
  else Future.failed(new IllegalArgumentException)
}

val value2: Int = 45

val triedDangerousOperation2: Try[Future[Int]] = Try {
  doSomethingDangerous2(value2)}

val future: Future[Int] = triedDangerousOperation2 match {
  case Success(eventualInt) => println(s"It works: $eventualInt"); eventualInt
  case _ => println("No"); Future.successful(-1)
}

// TODO decomment
// Await.result(future, Duration(2, TimeUnit.SECONDS)) // bang!!!
// Await.result(future.recover({case _ => -1}), Duration(2, TimeUnit.SECONDS)) // bang!!!

