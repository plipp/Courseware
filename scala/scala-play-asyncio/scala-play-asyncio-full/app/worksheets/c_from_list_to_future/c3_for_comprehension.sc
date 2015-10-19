import java.util.concurrent.TimeUnit

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

// flatMap --> for comprehension

// Lists
// Ziel: List((1,10), (2,10), (3,10), (1,20), (2,20), (3,20), (1,30), (2,30), (3,30))
val flatList1 = List(1,2,3)
val flatList2 = List(10,20,30)

for {j <- flatList2
     i <- flatList1} yield (i,j)

// Futures
// Ziel: Future[(Int, Int)]
val future1 = Future {1}
val future2 = Future {2}
val futureOfTuples = for {j <- future2
                          i <- future1} yield (i,j)
val tuple = Await.result(futureOfTuples, Duration(2, TimeUnit.SECONDS))
