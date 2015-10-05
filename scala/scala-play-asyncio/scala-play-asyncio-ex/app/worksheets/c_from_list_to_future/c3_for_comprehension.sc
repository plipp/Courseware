import java.util.concurrent.TimeUnit

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

// flatMap --> for comprehension

// Lists
val flatList1 = List(1,2,3)
val flatList2 = List(10,20,30)

for {j <- flatList2
     i <- flatList1} yield (i,j)

// Futures
val future1 = Future {1}
val future2 = Future {2}
val futureOfTuples = for {j <- future2
                          i <- future1} yield (i,j)
val tuple = Await.result(futureOfTuples, Duration(2, TimeUnit.SECONDS))

// TODO show with Future.failed + recovery