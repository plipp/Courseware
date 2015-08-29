import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Try, Success, Failure}

val counter = -100
val possiblyFailingFuture = Future {
  if (counter < 0) throw new IllegalArgumentException else counter
}

// ------------------------------------------------------------------
// onX
val mappedFeature = possiblyFailingFuture.map(counter => counter * 2)
var result = -100 // mutable!
mappedFeature.onComplete((eventualCounter: Try[Int]) =>
  eventualCounter match {
    case Success(cnt) => result = cnt
    case Failure(_) => result = 0
  })
result

