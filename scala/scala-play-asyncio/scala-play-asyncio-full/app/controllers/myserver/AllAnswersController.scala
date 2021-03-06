package controllers.myserver

import play.api.Logger
import play.api.mvc._

import scala.concurrent.Future
import scala.util.Try

class AllAnswersController extends Controller {

  /**
   *  1. Call e.g. in browser with:
   *     - http://localhost:9000/doAnswer?question=how => 42
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
   *     - http://localhost:9000/calculate?number=5  ==> 4
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

  /**
   *  1. Call e.g. in browser with:
   *     - http://localhost:9000/doCalculate?number=5  ==> 4
   */
  def doBlock (): Action[AnyContent] = Action.async { /* Action.async(block: R[AnyContent] => Future[Result]): Action[AnyContent]*/
    request => {
      Logger.info("BEGIN: doBlock")
      Thread.sleep(120000) // 2 min
      Logger.info("END  : doBlock")

      Future.successful(Ok("Thank you for waiting..."))
    }
  }
}
