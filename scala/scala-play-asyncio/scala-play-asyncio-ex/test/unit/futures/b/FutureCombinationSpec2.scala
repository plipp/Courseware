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

@RunWith(classOf[JUnitRunner])
// REQUIRES: Started Play!!! (=>this is actually not a unit test)
class FutureCombinationSpec2 extends Specification with BeforeAfterAll {

  // helper

  def fetchContentAsInt(url:String)(implicit ws: WSClient): Future[Int] = {
    ws.url(url).get().map(response => response.body.toInt)
  }

  // ----------------------------------------------------------------------------
  // 1. code under test: FIXME
  /**
   * calls
   * 1) "http://localhost:9000/doAnswer?question=howMany" -> answer1
   * 2) "http://localhost:9000/doAnswer?question=Again" -> answer2
   * 3) "http://localhost:9000/doCalculate?number=$(answer1 + answer2)
   * @return the result-Future
   */

  // TODO: explain when the Futures start to fetch data in your implementation
  def combineWithFor(): Future[Int] = ???

  // test
  "combineWithFor" should {
    "calculate combined result" in {
      val result = Await.result(combineWithFor(), Duration.Inf)
      result mustEqual 142 //== (101+42)-1
    }
  }

  // ----------------------------------------------------------------------------
  // 2. code under test: FIXME
  /**
   * calls
   * 1) "http://localhost:9000/doAnswer?question=howMany" -> answer1
   * 2) "http://localhost:9000/doAnswer?question=Again" -> answer2
   * 3) "http://localhost:9000/doCalculate?number=$(answer1 + answer2)
   * @return the result-Future
   */

  // TODO: explain when the Futures start to fetch data in your implementation
  def combineWithAsyncAwait(): Future[Int] = ???

  // test
  "combineWithAsyncAwait" should {
    "calculate combined result" in {
      val result = Await.result(combineWithAsyncAwait(), Duration.Inf)
      result mustEqual 142 //== (101+42)-1
    }
  }

  // ----------------------------------------------------------------------------
  // 3. code under test: FIXME
  /**
   * calls
   * 1) "http://localhost:9000/doAnswer?question=howMany" -> answer1
   * 2) "http://localhost:9000/doAnswer?question=Again" -> answer2
   * 3) "http://localhost:9000/doCalculate?number=$(answer1 + answer2)
   * @return the result-Future
   */
  def combineWithFlatMap(): Future[Int] = ???

  // test
  "combineWithFlatMap" should {
    "calculate combined result" in {
      val result = Await.result(combineWithFlatMap(), Duration.Inf)
      result mustEqual 142 //== (101+42)-1
    }
  }

  // of course in real-live wsClient should be   m o c k e d!
  implicit val wsClient: WSClient = NingWSClient()

  override def beforeAll(): Unit = println ("BEGIN Spec")

  override def afterAll(): Unit = wsClient.close(); println ("END  Spec")
}
