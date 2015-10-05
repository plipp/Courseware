import java.util.concurrent.TimeUnit
import play.api.libs.ws.ning.NingWSClient
import play.api.libs.ws.{WSClient, WSRequest, WSResponse}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

// -----------------------------------------------------
def doANonBlockingNetworkCall(uri: String)(implicit wsClient: WSClient): Future[Int] = {
  println("BEGIN: doANonBlockingNetworkCall")

  val proxiedRequest: WSRequest = wsClient.url(uri)
  val eventualResponse: Future[WSResponse] = proxiedRequest.get()

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
// call websites
// - http://www.heise.de
// - http://blackhole.webpagetest.org
// peek into
// get results (BLOCKING way)
// ============================================================================
