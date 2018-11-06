package scala.PFPP.TinyWeb.View

import scala.PFPP.TinyWeb.TinyWeb.RenderingException

trait View {
  def render(model: Map[String, List[String]]): String
}

class FunctionView(viewRenderer: Map[String, List[String]] => String) extends View {
  override def render(model: Map[String, List[String]]): String = {
    try {
      viewRenderer(model)
    } catch {
      case e: Exception => throw RenderingException(e)
    }
  }
}
