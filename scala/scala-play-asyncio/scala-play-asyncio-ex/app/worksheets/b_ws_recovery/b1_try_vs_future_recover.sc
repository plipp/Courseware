import java.util.concurrent.TimeUnit

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
// ------------------------------------------------------------------
// Try
def doSomethingDangerous(i: Int): Int = {
  if (i % 2 == 0) throw new IllegalArgumentException
  else i * 99
}

// TODO call with 1, 2 ...
doSomethingDangerous(1)

// ------------------------------------------------------------------
// Future
def doSomethingDangerous2(i: Int): Future[Int] = {
  if (i % 2 == 0) Future.failed(new IllegalArgumentException)
  else Future.successful(i*99)
}

// TODO call with 1, 2 ...
val intResult = Await.result(doSomethingDangerous2(1: Int), Duration(2, TimeUnit.SECONDS))

