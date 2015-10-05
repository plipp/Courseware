import java.util.concurrent.TimeUnit

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

// List
val sum = List(1,2,3,4).fold(0)(_+_)

// Future
val future1 = Future {1}
val future2 = Future {2}
val future3 = Future {3}
val future4 = Future {4}

val sumFuture = Future.fold(List(future1,future2,future3,future4))(0)(_+_)
val resultOfSumFuture = Await.result(sumFuture, Duration(2000, TimeUnit.SECONDS))

// ... reduce ...