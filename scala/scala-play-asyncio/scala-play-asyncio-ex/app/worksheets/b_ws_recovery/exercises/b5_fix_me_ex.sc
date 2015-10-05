import java.util.concurrent.TimeUnit

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global

// ------------------------------------------------------------------
def doSomethingDangerous (i:Int): Future[Int] = {
  if (i%2==0) throw new IllegalArgumentException("not divisible by 2")
  else Future.failed(new IllegalArgumentException("something else is wrong"))
}

val value = 45
val future: Future[Int] = doSomethingDangerous(value)
val result = Await.result(future, Duration(2, TimeUnit.SECONDS))

// ------------------------------------------------------------------
// fix it, so that for all 'value's in case of any error 0 is returned
