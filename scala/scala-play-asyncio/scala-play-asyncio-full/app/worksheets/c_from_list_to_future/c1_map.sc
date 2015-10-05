import java.util.concurrent.TimeUnit

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

// Lists
val flatList = List(1,2,3)
val mappedList = flatList map ( i => String.valueOf(i*2) )

// Futures
val future = Future {1}
val mappedFuture = future map ( i => String.valueOf(i*99))

// resolve future: blocking
Await.result(future, Duration(2, TimeUnit.SECONDS))
Await.result(mappedFuture, Duration(2, TimeUnit.SECONDS))
