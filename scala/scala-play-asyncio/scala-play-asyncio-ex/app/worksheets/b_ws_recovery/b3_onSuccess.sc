import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

val counter = -100
val possiblyFailingFuture = Future {if (counter<0) throw new IllegalArgumentException else counter}

// ------------------------------------------------------------------
// Callbacks I
possiblyFailingFuture.onFailure({case _:IllegalArgumentException => println("Oh no")})
possiblyFailingFuture.onSuccess({case cnt => println("Success")})
possiblyFailingFuture.value
possiblyFailingFuture.isCompleted
