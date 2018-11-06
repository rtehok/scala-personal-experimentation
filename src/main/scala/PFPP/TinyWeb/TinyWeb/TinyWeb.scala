package scala.PFPP.TinyWeb.TinyWeb

import scala.PFPP.TinyWeb.Controller.Controller

class TinyWeb(controllers: Map[String, Controller], filters: List[HttpRequest => HttpRequest]) {

  def handleRequest(httpRequest: HttpRequest): Option[HttpResponse] = {
    val composedFilters: HttpRequest => HttpRequest = filters.reverse.reduceLeft((composed, next) => composed compose next)
    val filteredRequest: HttpRequest = composedFilters(httpRequest)
    val controllerOption = controllers.get(filteredRequest.path)
    controllerOption map { controller => controller.handleRequest(filteredRequest) }
  }

}
