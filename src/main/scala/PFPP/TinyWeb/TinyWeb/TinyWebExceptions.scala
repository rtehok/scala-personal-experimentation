package scala.PFPP.TinyWeb.TinyWeb

class TinyWebExceptions {

}

case class ControllerException(statusCode: Int) extends RuntimeException

case class RenderingException(e: Exception) extends RuntimeException
