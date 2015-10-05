import org.apache.http.HttpResponse
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClientBuilder
import play.api.Logger

// TODO
// - Logger accessible
// - recall currying
def doABlockingNetworkCall(uri: String)(implicit http: HttpClient): String = {
  // Code should just illustrate blocking I/O.
  // D O N ' T    do this in your real-world code !!!
  Logger.info("BEGIN: doABlockingNetworkCall")

  val response:HttpResponse = http.execute(new HttpGet(uri)) // blocks main-thread!
  val status: String = response.getStatusLine.getReasonPhrase

  Logger.info("END  : doABlockingNetworkCall")
  status
}

implicit val httpClient = HttpClientBuilder.create().build()

// -----------------------------------------------------
// call a - hopefully - reachable website
val statusOfHeiseCall = doABlockingNetworkCall("http://www.heise.de")


// -----------------------------------------------------
// call a forever-blocking website
// val statusOfBlackHole = doABlockingNetworkCall("http://blackhole.webpagetest.org")
println("================== FINISHED")

