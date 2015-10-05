import java.util.concurrent.TimeUnit

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

val counter = -1
val possiblyFailingFuture = Future {if (counter<0) throw new IllegalArgumentException else counter}

// TODO decomment
// Await.result(possiblyFailingFuture, Duration(2, TimeUnit.SECONDS))

// ------------------------------------------------------------------
// fix it, so that in case of an IllegalArgumentException 0 is returned
//
// 1. recover/recoverWith
// 2. fallback