import java.util.concurrent.TimeUnit
import play.api.libs.ws.ning.NingWSClient
import play.api.libs.ws.{WSClient, WSRequest, WSResponse}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Awaitable, Await, Future}
// -----------------------------------------------------
def doANonBlockingNetworkCall(uri: String)(implicit wsClient: WSClient): Future[Int] = {
  println("BEGIN: doANonBlockingNetworkCall")

  val proxiedRequest: WSRequest = wsClient.url(uri)
  val eventualResponse: Future[WSResponse] = proxiedRequest.get()

  val eventualResult: Future[Int] =
    eventualResponse.map(response => {
      println("MAP(response=>):")
      response.body.toInt
    })

  println("END  : doANonBlockingNetworkCall")
  eventualResult
}
implicit val wsClient: WSClient = NingWSClient()
// -----------------------------------------------------

// EXERCISE
// call
// 1) "http://localhost:9000/doAnswer?question=howMany" -> answer
// 2) "http://localhost:9000/doCalculate?number=<answer> with answer from 1
// --> expected result 42*2 = 84
// Use:
// a) for-comprehension
// b) async-await


val eventualInt = ???

val theAnswer = Await.result(eventualInt, Duration(1, TimeUnit.SECONDS))
println (s"The Answer is $theAnswer")