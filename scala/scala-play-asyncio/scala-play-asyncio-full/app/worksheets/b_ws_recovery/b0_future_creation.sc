import java.util.concurrent.TimeUnit

import scala.util.{Failure, Success}
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global

// successful future
val successfulFuture = Future {1}
successfulFuture.isCompleted
successfulFuture.value

val successfulFuture1 = Future.successful(1)  // Future => Promise
successfulFuture1.isCompleted
successfulFuture1.value     // Future => Try

// failing future
val failingFuture = Future {throw new IllegalStateException}
failingFuture.isCompleted
failingFuture.value
val failingFuture1 = Future.failed(new IllegalArgumentException)
failingFuture1.isCompleted
failingFuture1.value

// successful or failing
val successfulFutureFromTry = Future.fromTry[Int](Success(10))
val failingFutureFromTry = Future.fromTry[Int](Failure(new IllegalArgumentException))

// blocking resolve
val successfulResult = Await.result(successfulFuture, Duration(2000, TimeUnit.SECONDS))
val successfulResultFromTry = Await.result(successfulFutureFromTry, Duration(2000, TimeUnit.SECONDS))
val failingResult = Await.result(failingFuture, Duration(2000, TimeUnit.SECONDS))