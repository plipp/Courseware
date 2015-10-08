import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

val counter = -200
val possiblyFailingFuture = Future {
  if (counter < 0) throw new IllegalArgumentException else counter
}

// ------------------------------------------------------------------
// Callbacks II
possiblyFailingFuture.onComplete((eventualCounter: Try[Int]) =>
  eventualCounter match {
    case Success(cnt) => println("Success")
    case Failure(_) => println("Oh no!")
  })

possiblyFailingFuture.value
possiblyFailingFuture.isCompleted

