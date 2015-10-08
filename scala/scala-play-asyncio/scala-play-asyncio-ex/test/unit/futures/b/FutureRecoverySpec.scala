package unit.futures.b

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

@RunWith(classOf[JUnitRunner])
class FutureRecoverySpec extends Specification {

  def givenDoSomethingDangerous(i: Int): Future[Int] = {
    if (i % 2 == 0) throw new IllegalStateException
    else if (i % 3 == 0) Future.failed(new IllegalArgumentException)
    else Future.successful(i*2)
  }

  // code under test: FIXME
  def consumeSomeThingDangerous(i: Int): Future[Int] = givenDoSomethingDangerous(i)

  // ----------------------------------------------------------------------
  // the test
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
