package unit.futures.b

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import org.specs2.specification.BeforeAfterAll
import play.api.libs.ws.WSClient
import play.api.libs.ws.ning.NingWSClient

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

// requires extra library
import scala.async.Async.async

@RunWith(classOf[JUnitRunner])
class FutureCombinationSpec1 extends Specification with BeforeAfterAll {


  def computeSquare(i: Int): Future[Int] = ???

  // test
  "computeSquare" should {
    "square 2" in {
      val result = Await.result(computeSquare(2), Duration.Inf)
      result mustEqual 4
    }
  }

  def computeSquare(i: Future[Int]): Future[Int] = ???

  // test
  "computeSquare" should {
    "square 2" in {
      val result = Await.result(computeSquare(Future(2)), Duration.Inf)
      result mustEqual 4
    }
  }

  class SumSequence(start: Int, stop: Int, delay: Long = 0) {
    def perform(): Int = {
      require(start >= 0)
      Thread.sleep(delay)
      (start to stop).sum
    }
  }

  def findSumOfSums (sums: Seq[SumSequence]): Future[Int] = {
    ???
  }

  // test
  "findSumOfSums" should {
    "return 100" in {
      val delayedSumSeq1 = new SumSequence(0,100,2000)
      val delayedSumSeq2 = new SumSequence(0,100,1000)

      val result = Await.result(findSumOfSums(Seq(delayedSumSeq1, delayedSumSeq2)), Duration.Inf)
      result mustEqual 2*5050
    }
  }

  override def beforeAll(): Unit = println ("BEGIN Spec")

  override def afterAll(): Unit = println ("END  Spec")
}
