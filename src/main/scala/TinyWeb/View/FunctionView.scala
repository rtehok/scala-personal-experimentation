package scala.TinyWeb.View

import scala.TinyWeb.TinyWeb.RenderingException

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
