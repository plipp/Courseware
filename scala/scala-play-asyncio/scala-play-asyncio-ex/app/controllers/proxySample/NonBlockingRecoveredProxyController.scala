package controllers.proxySample

import java.net.ConnectException

import play.api.Logger
import play.api.Play.current
import play.api.libs.ws.{WS, WSRequest, WSResponse}
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class NonBlockingRecoveredProxyController extends Controller {

  /**
   *  1. Call e.g. in browser with:
   *     - http://localhost:9000/recoveredProxy
   *
   *  2. See Browser-Console (F12)
   *
   *  3. See Server-Log for url=http://blackhole.webpagetest.org
   *     a) Threads
   *     b) InternalServerError
   *
   */
    def doProxy (): Action[AnyContent] = Action.async { /* Action.async(block: R[AnyContent] => Future[Result]): Action[AnyContent]*/
      request => {
        Logger.info("BEGIN: doProxy(recovered)")
        val proxiedUrl: String = request.getQueryString("url").getOrElse("http://blackhole.webpagetest.org")
        val proxiedRequest: WSRequest = WS.url(proxiedUrl)

        val eventualResponse: Future[WSResponse] = proxiedRequest.get()

        val eventualResult: Future[Result] =
          eventualResponse.map(response => {
            Logger.info("MAP(response=>): doProxy(recovered)")
            Status(response.status)(response.body).as(response.header("Content-Type").getOrElse("application/octet-stream"))
          }).recover({case _:ConnectException => Logger.warn(s"Service Not Available: $proxiedUrl"); ServiceUnavailable})

        Logger.info("END  : doProxy(recovered)")
        eventualResult
      }
    }
}
