import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

val counter = -200
val possiblyFailingFuture = Future {
  if (counter < 0) throw new IllegalArgumentException else counter
}

// ------------------------------------------------------------------
// Callbacks II
var result = -100 // mutable!
possiblyFailingFuture.onComplete((eventualCounter: Try[Int]) =>
  eventualCounter match {
    case Success(cnt) => result = cnt
    case Failure(_) => result = 0
  })

possiblyFailingFuture.value
result

