import java.util.concurrent.TimeUnit

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.util.Try

// ------------------------------------------------------------------
// Try
def doSomethingDangerous(i: Int): Int = {
  if (i % 2 == 0) throw new IllegalArgumentException
  else i * 99
}

val value: Int = 44

val triedDangerousOperation: Try[Int] = Try {
  doSomethingDangerous(value)
}

val intValue: Int = triedDangerousOperation.recover({case _ => -1}).get
val intValue2: Int = triedDangerousOperation.recoverWith({case _ => Try{-1}}).get

// TODO: andere Auflösungsmöglichkeiten?

// ------------------------------------------------------------------
// Future
def doSomethingDangerous2(i: Int): Future[Int] = {
  if (i % 2 == 0) Future.failed(new IllegalArgumentException)
  else Future.successful(i + 99)
}

val value2: Int = 44

val future: Future[Int] = doSomethingDangerous2(value2)
future.isCompleted
future.value

val intResult = Await.result(future.recover({case _ => -1}), Duration(2, TimeUnit.SECONDS)) // bang!!!

