import java.util.concurrent.TimeUnit

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

// Lists
val flatList1 = List(1,2,3)
val flatList2 = List(10,20,30)
val doubleMappedList = flatList2 map (j => flatList1 map (i => (i,j))) // Lists in List
val doubleFlatMappedList = flatList2 flatMap (j => flatList1 map (i => (i,j)))

// Futures
val future1 = Future {1}
val future2 = Future {2}
val doubleMappedFuture = future2 map (j => future1 map (i => (i,j))) // Future in Future
val doubleFlatMappedFuture = future2 flatMap (j => future1 map (i => (i,j)))

val futureOfTuples =
  Await.result(doubleMappedFuture, Duration(2, TimeUnit.SECONDS))  // resolves to Future[(1,2)]
val tuples =
  Await.result(doubleFlatMappedFuture, Duration(2, TimeUnit.SECONDS)) // resolves to (1,2)
