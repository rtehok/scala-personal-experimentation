def discount(percent: Double): Double => Double = {
  if (percent < 0 || percent >= 100) throw new IllegalArgumentException("Must be between 0 and 100")
  originalPrice: Double => originalPrice * (1 - percent / 100)
}

val twentyFivePercentOff = discount(25)

(Vector(100.0, 25.0, 50.0, 25.0) map twentyFivePercentOff).sum


import scala.annotation.tailrec

def selector(path: Symbol*): Map[Symbol, Any] => Option[Any] = {
  if (path.isEmpty) throw new IllegalArgumentException("path must not be empty")

  @tailrec
  def helper(pathSeq: Seq[Symbol], ds: Map[Symbol, Any]): Option[Any] = {
    if (pathSeq.size == 1) {
      ds.get(pathSeq.head)
    } else {
      ds.get(pathSeq.head) match {
        case Some(currentMap: Map[Symbol, Any]) => helper(pathSeq.tail, currentMap)
        case None => None
        case _ => None
      }
    }
  }

  ds: Map[Symbol, Any] => helper(path.toSeq, ds)
}

val simplePerson = Map('name -> "Michael Bevilacqua-Linn")
val name = selector('name)
name(simplePerson)


val moreComplexPerson =  Map('name -> Map('first -> "Michael", 'last -> "Bevilacqua-Linn"))
val firstName = selector('name, 'first)
firstName(moreComplexPerson)

val middleName = selector('name, 'middle)
middleName(moreComplexPerson)