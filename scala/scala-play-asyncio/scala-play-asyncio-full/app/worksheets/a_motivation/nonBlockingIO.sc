import java.util.concurrent.TimeUnit
import play.api.libs.ws.ning.NingWSClient
import play.api.libs.ws.{WSClient, WSRequest, WSResponse}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

// -----------------------------------------------------
def doANonBlockingNetworkCall(uri: String)(implicit wsClient: WSClient): Future[Int] = {
  println("BEGIN: doANonBlockingNetworkCall")

  val request: WSRequest = wsClient.url(uri)
  val eventualResponse: Future[WSResponse] = request.get()

  val eventualResult: Future[Int] =
    eventualResponse.map(response => {
      println("MAP(response=>):")
      response.status
    })

  println("END  : doANonBlockingNetworkCall")
  eventualResult
}
implicit val wsClient: WSClient = NingWSClient()

// -----------------------------------------------------

// ============================================================================
// call a - hopefully - reachable website
val eventualResultOfHeiseCall = doANonBlockingNetworkCall("http://www.heise.de")
// -----------------------------------------------------
// call a forever-blocking website
val eventualResultOfBlackHole = doANonBlockingNetworkCall("http://blackhole.webpagetest.org")
// ============================================================================

println("================== FINISHED ? No ==> get the real results ...")

// peek into futures
val heiseValue = eventualResultOfHeiseCall.value
eventualResultOfHeiseCall.isCompleted
val blackHoleValue = eventualResultOfBlackHole.value
eventualResultOfBlackHole.isCompleted

// await for real results (BLOCKING)
val realHeiseResultStatus = Await.result(eventualResultOfHeiseCall, Duration(1, TimeUnit.SECONDS))
val heiseValueAfterAwait = eventualResultOfHeiseCall.value
eventualResultOfHeiseCall.isCompleted
// TODO: recall Try->Success
val realBlackHoleStatus = try {
  Await.result(eventualResultOfBlackHole, Duration(1, TimeUnit.SECONDS))
} catch {
  case e: Throwable => println(s"Black Hole timed out...$e")
    -1
}
val blackHoleValueAfterAwait = eventualResultOfBlackHole.value
eventualResultOfBlackHole.isCompleted
println("================== really FINISHED")
