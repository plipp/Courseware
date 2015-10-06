package unit.futures.b

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.util.Try

@RunWith(classOf[JUnitRunner])
class FutureRecoverySpec extends Specification {

  def givenDoSomethingDangerous(i: Int): Future[Int] = {
    if (i % 2 == 0) throw new IllegalStateException
    else if (i % 3 == 0) Future.failed(new IllegalArgumentException)
    else Future.successful(i)
  }

  // code under test: FIXME
  def consumeSomeThingDangerous(i: Int): Future[Int] =
    Try{givenDoSomethingDangerous(i)}
      .recover({case t: Throwable => Future.failed(t)}).get
      .map(result => result * 2)
      .recover({case _ => -1})

  "consumeSomeThingDangerous" should {
    "multiply allowed values with 2" in {
      val result = Await.result(consumeSomeThingDangerous(11), Duration.Inf)
      result mustEqual 22
    }
    "return -1 in case of multiples of 2" in {
      val result = Await.result(consumeSomeThingDangerous(14), Duration.Inf)
      result mustEqual -1
    }
    "return -1 in case of multiples of 3" in {
      val result = Await.result(consumeSomeThingDangerous(9), Duration.Inf)
      result mustEqual -1
    }
    "return -1 in case of multiples of 2 and 3" in {
      val result = Await.result(consumeSomeThingDangerous(6), Duration.Inf)
      result mustEqual -1
    }
  }
}
