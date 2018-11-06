package scala.PFPP.TinyWeb.Controller

import scala.PFPP.TinyWeb.TinyWeb.{ControllerException, HttpRequest, HttpResponse, RenderingException}
import scala.PFPP.TinyWeb.View.View

trait Controller {
  def handleRequest(httpRequest: HttpRequest): HttpResponse
}

class FunctionController(view: View, doRequest: HttpRequest => Map[String, List[String]]) extends Controller {
  override def handleRequest(httpRequest: HttpRequest): HttpResponse =
    try {
      val model: Map[String, List[String]] = doRequest(httpRequest)
      val responseBody: String = view.render(model)
      HttpResponse(responseBody, 200)
    } catch {
      case e: ControllerException => HttpResponse("", e.statusCode)
      case _: RenderingException => HttpResponse("Exception while rendering.", 500)
      case _: Exception => HttpResponse("", 500)
    }
}
