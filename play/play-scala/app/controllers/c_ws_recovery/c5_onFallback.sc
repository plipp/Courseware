import java.util.concurrent.TimeUnit

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success, Try}

val failingFuture = Future.failed(new IllegalArgumentException)
val fallbackFuture = Future.successful(99)

// TODO decomment
// Await.result(failingFuture, Duration(2, TimeUnit.SECONDS))
// Await.result(failingFuture.fallbackTo(fallbackFuture), Duration(2, TimeUnit.SECONDS))
