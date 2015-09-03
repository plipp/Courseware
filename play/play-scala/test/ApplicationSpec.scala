import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import play.api.mvc

import play.api.test._
import play.api.test.Helpers._

import scala.concurrent.Future

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
@RunWith(classOf[JUnitRunner])
class ApplicationSpec extends Specification {

  "Application" should {

    "send 404 on a bad request" in new WithApplication{
      route(FakeRequest(GET, "/boum")) must beSome.which (status(_) == NOT_FOUND)
    }

    "render the index page" in new WithApplication{
      val home: Future[mvc.Result] = route(FakeRequest(GET, "/")).get

      status(home) must equalTo(OK)
      contentType(home) must beSome.which(_ == "text/html")
      contentAsString(home) must contain ("Your new application is ready.")
    }

    "proxy a url" in new WithApplication{
      // TODO mock heise-call
      val proxy: Future[mvc.Result] = route(FakeRequest(GET, "/proxy?url=http://www.heise.de")).get

      status(proxy) must equalTo(OK)
      contentType(proxy) must beSome.which(_ == "text/html")
      contentAsString(proxy) must contain ("heise")
    }
  }
}
