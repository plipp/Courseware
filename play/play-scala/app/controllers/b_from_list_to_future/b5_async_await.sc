import java.util.concurrent.TimeUnit

// requires extra library
import scala.async.Async._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

// Future only - async-await==blocking
val future1 = Future.successful(-100)
val future2 = Future.successful(100)
val future3 = Future.successful(42)

val combined = async { // non-blocking
  val val1 = await(future1)  // suspending until val1 is available
  if (val1>0) {
    val1 + 2*await(future2)  // suspending until val2 is available
  } else {
    await(future3) // suspending until val3 is available
  }
}

Await.result(combined, Duration(2000, TimeUnit.SECONDS))



