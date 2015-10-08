import play.api.libs.ws.ning.NingWSClient
import play.api.libs.ws.{WSClient, WSRequest, WSResponse}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

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
// call websites
// - http://www.heise.de or
//   http://localhost:9000/doCalculate (-> see controller)
// - http://blackhole.webpagetest.org or
//   http://localhost:9000/doCalculate (-> see controller)
// peek into
// get results (BLOCKING way)
// ============================================================================
