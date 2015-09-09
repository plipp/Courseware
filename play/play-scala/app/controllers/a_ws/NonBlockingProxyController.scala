package controllers.a_ws

import com.google.inject.Inject
import play.api.Logger
import play.api.Play.current
import play.api.libs.ws.{WSClient, WSRequest, WSResponse}
import play.api.mvc.{Action, AnyContent, Controller, Result}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class NonBlockingProxyController @Inject() (ws: WSClient) extends Controller {

  /**
   * 0  application.conf -> play.ws.timeout.connection
   *
   * 1. Call e.g. in browser with:
   * - http://localhost:9000/proxy?url=http://www.heise.de ...
   * - http://localhost:9000/proxy
   *
   * 2. See Browser-Console (F12)
   *
   * 3. See Server-Log for http://localhost:9000/proxy (no url)
   * a) Threads
   * b) InternalServerError => We need an error handling!
   */
  def doProxy(): Action[AnyContent] = Action.async {
    /* Action.async(block: R[AnyContent] => Future[Result]): Action[AnyContent]*/
    request => {
      Logger.info("BEGIN: doProxy (non-blocking)")
      val proxiedRequest: WSRequest =
        ws.url(request.getQueryString("url").getOrElse("http://blackhole.webpagetest.org"))

      val eventualResponse: Future[WSResponse] = proxiedRequest.get()

      val eventualResult: Future[Result] =
        eventualResponse.map(response => {
          Logger.info("MAP(response=>): doProxyHeise")
          Ok(views.html.index(s"${response.body.substring(0, 500)} ..."))
        })

      Logger.info("END  : doProxy doProxy (non-blocking)")
      eventualResult
    }
  }
}
