import java.util.concurrent.TimeUnit

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

// Lists
val flatList = List(1,2,3)
// TODO map*99

// Futures
val future = Future {1}
// TODO map*99

// resolve future: blocking
Await.result(future, Duration(2, TimeUnit.SECONDS))
// TODO await mapped result
