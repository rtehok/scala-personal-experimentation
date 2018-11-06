package scala.PFPP.TinyWeb

import scala.PFPP.TinyWeb.Controller.FunctionController
import scala.PFPP.TinyWeb.TinyWeb.{HttpRequest, TinyWeb}
import scala.PFPP.TinyWeb.View.FunctionView
import scala.util.Random

class Example {

  def greetingViewRenderer(model: Map[String, List[String]]): String = {
    "<h1>Friendly Greetings:%s</h1>".format(
      model
        getOrElse("greetings", List[String]())
        map renderGreeting
        mkString ", "
    )
  }

  def greetingView = new FunctionView(greetingViewRenderer)

  def handleGreetingRequest(request: HttpRequest) =
    Map("greetings" -> request.body.split(",").toList.map(makeGreeting))

  def greetingController: FunctionController = new FunctionController(greetingView, handleGreetingRequest)

  def loggingFilter(request: HttpRequest): HttpRequest = {
    println("In Logging Filter - requestFilter for path: %s".format(request.path))
    request
  }

  def tinyWeb = new TinyWeb(Map("/greeting" -> greetingController), List(loggingFilter))

  def testHttpRequest = HttpRequest(Map(), body = "toto,tata,titi", path = "/greeting")


  private def renderGreeting(greeting: String): String = "<h2>%s</h2>".format(greeting)

  private def random = new Random()

  private def greetings = Vector("Hello", "Ola", "Salut", "Greetings")

  private def makeGreeting(name: String): String = "%s, %s".format(greetings(random.nextInt(greetings.size)), name)

}
