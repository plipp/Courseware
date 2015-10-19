import java.util.concurrent.TimeUnit

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future, Promise}

val f98: Future[Int] = Future.successful(98)
val f99: Future[Int] =  Future {
  Thread.sleep(500)
  99
}

// combines futures
def first[T](f: Future[T], g: Future[T]): Future[T] = {
  val p = Promise[T]()
  f onSuccess {
    case x => println ("f successful"); p.trySuccess(x)
  }
  g onSuccess {
    case x => println ("g successful"); p.trySuccess(x)
  }
  p.future
}

val result = Await.result(first(f99, f98), Duration(2000, TimeUnit.SECONDS))

println ("FINISHED")