import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

val counter = -100
val possiblyFailingFuture = Future {if (counter<0) throw new IllegalArgumentException else counter}

// ------------------------------------------------------------------
// onX
val mappedFeature = possiblyFailingFuture.map(counter => counter*2)
var result = -100 // mutable!
mappedFeature.onFailure({case _:IllegalArgumentException => result = 0})
mappedFeature.onSuccess({case cnt => result = cnt})
result

