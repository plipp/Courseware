package controllers.a_ws

import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClientBuilder
import play.api.Logger
import play.api.libs.ws.{WS, WSRequest}
import play.api.mvc.{Action, Controller}

class BlockingProxyController extends Controller {

  /**
   *  1. Call e.g. in browser with:
   *     - http://localhost:9000/proxyBlocking?url=http://www.heise.de
   *     - http://localhost:9000/proxyBlocking ...
   *
   *  2. Take a look in the server-log: Threads ... no url=>InternalServerError after a while
   */
  def doProxy () = Action {  request =>
    val uri: String = request.getQueryString("url").getOrElse("http://blackhole.webpagetest.org")

    // Code should just illustrate blocking I/O.
    // D O N ' T    do this in your real-world code !!!
    Logger.info("BEGIN: doProxy (blocking)")

    val httpClient = HttpClientBuilder.create().build()

    val response = httpClient.execute(new HttpGet(uri)) // blocks main-thread!
    val status: String = response.getStatusLine.getReasonPhrase

    Logger.info("END  : doProxy (blocking)")

    Ok(views.html.index(s"response from $uri: $status"))
  }
}
