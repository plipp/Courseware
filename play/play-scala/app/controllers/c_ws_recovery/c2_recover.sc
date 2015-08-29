import java.util.concurrent.TimeUnit

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

val counter = -1
val possiblyFailingFuture = Future {if (counter<0) throw new IllegalArgumentException else counter}

// TODO decomment
// Await.result(possiblyFailingFuture, Duration(2, TimeUnit.SECONDS))

// ------------------------------------------------------------------
// recover
val mappedFeature = possiblyFailingFuture.map(counter => counter*2)
Await.result(mappedFeature.recover({case _:IllegalArgumentException => 0}), Duration(2, TimeUnit.SECONDS))

// TODO decomment
// Await.result(mappedFeature.recover({case _:NullPointerException => 0}), Duration(2, TimeUnit.SECONDS))

