import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

//import scala.concurrent.duration.Duration
//import scala.concurrent.{Await, Future}

// ---------------------------------------------------------
val flatList1 = List(1,2,3)
val flatList2 = List(10,20,30)
// TODO ==> List((1,10), (2,10), (3,10), (1,20), (2,20), (3,20), (1,30), (2,30), (3,30))

// ---------------------------------------------------------
// Futures
val future1 = Future {1}
val future2 = Future {2}
// TODO ==> Future[(Int,Int)]
