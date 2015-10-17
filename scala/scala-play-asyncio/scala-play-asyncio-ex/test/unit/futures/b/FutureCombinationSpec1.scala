package unit.futures.b

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import play.api.Logger

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

@RunWith(classOf[JUnitRunner])
class FutureCombinationSpec1 extends Specification {


  // TODO implement
  def computeSquare(i: Int): Future[Int] = ???

  // test
  "computeSquare" should {
    "square 2" in {
      val result = Await.result(computeSquare(2), Duration.Inf)
      result mustEqual 4
    }
  }

  // TODO implement
  def computeSquare(eventualI: Future[Int]): Future[Int] = ???

  // test
  "computeSquare2" should {
    "square 2" in {
      val result = Await.result(computeSquare(Future(2)), Duration.Inf)
      result mustEqual 4
    }
  }

  // ----------------------------------------------------------------

  class SumSequence(start: Int, stop: Int, delay: Long = 0) {
    def perform(): Int = {
      require(start >= 0)
      Logger.info ("----------------------- SumSequence STARTED")
      Thread.sleep(delay)
      (start to stop).sum
    }
  }

  // TODO
  // - implement
  // - check threads/times in the logging output!
  def findSumOfSumsSequentially(sumSequences: Seq[SumSequence]): Future[Int] = ???

  // TODO
  // - implement
  // - check threads/times in the logging output!
  def findSumOfSumsInParallel(sumSequences: Seq[SumSequence]): Future[Int] = ???

  // test
  "findSumOfSums" should {
    val delay: Long = 2000

    val sumSequences: Seq[SumSequence] = Seq(
      new SumSequence(0, 100, delay),
      new SumSequence(0, 100, delay),
      new SumSequence(0, 100, delay)
    )

    val n = sumSequences.size

    "return n*sum(0...1000) sequentially executed" in {

      val (result, duration) = Await.result(
        withTiming("findSumOfSums sequentially")(findSumOfSumsSequentially(sumSequences)),
        Duration.Inf)

      result mustEqual n*5050
      duration must be_>= (n*delay)
    }

    "return n*sum(0...1000) in parallel executed" in {

      val (result, duration) = Await.result(
        withTiming("findSumOfSums in parallel")(findSumOfSumsInParallel(sumSequences)),
        Duration.Inf)

      result mustEqual n*5050
      duration must be between(delay, n*delay)
    }
  }

  private def withTiming[T] (description: String)(block: => Future[T]) : Future[(T, Long)] = {
    val start: Long = System.currentTimeMillis()
    block map { result =>
      val duration = System.currentTimeMillis() - start
      Logger.info(s"------------ Duration of '$description' in ms: $duration")
      (result, duration)
    }
  }
}
