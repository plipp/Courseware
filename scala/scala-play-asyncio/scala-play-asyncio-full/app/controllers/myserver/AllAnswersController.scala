package controllers.myserver

import play.api.Logger
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.Try

class AllAnswersController extends Controller {

  /**
   *  1. Call e.g. in browser with:
   *     - http://localhost:9000/doAnswer?question=how
   */
    def doAnswer (): Action[AnyContent] = Action.async { /* Action.async(block: R[AnyContent] => Future[Result]): Action[AnyContent]*/
      request => {
        Logger.info("BEGIN: doAnswer")
        val question: Option[String] = request.getQueryString("question")
        val answer = question match {
          case Some(q) =>Ok((if (q.startsWith("A")) 101 else 42).toString)
          case None => BadRequest("no question param set")
        }
        Logger.info("END  : doAnswer")
        Future.successful(answer)
      }
    }

  /**
   *  1. Call e.g. in browser with:
   *     - http://localhost:9000/calculate?number=5
   */
    def doCalculate (): Action[AnyContent] = Action.async { /* Action.async(block: R[AnyContent] => Future[Result]): Action[AnyContent]*/
      request => {
        Logger.info("BEGIN: doCalculate")
        val number:Int = request.getQueryString("number").flatMap(s=>Try{s.toInt}.toOption).getOrElse(0)
        val answer = if (number%2==0) number*2 else number-1
        Logger.info("END  : doCalculate")

        Future.successful(Ok(answer.toString))
      }
    }
}
