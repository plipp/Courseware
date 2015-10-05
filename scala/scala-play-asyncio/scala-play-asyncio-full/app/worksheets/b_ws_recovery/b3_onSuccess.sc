import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

val counter = -100
val possiblyFailingFuture = Future {if (counter<0) throw new IllegalArgumentException else counter}

// ------------------------------------------------------------------
// onX
var result = -100 // mutable!
possiblyFailingFuture.onFailure({case _:IllegalArgumentException => result = 0})
possiblyFailingFuture.onSuccess({case cnt => result = cnt})

possiblyFailingFuture.value
result

