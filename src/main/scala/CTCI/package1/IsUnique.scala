package CTCI.package1

import scala.annotation.tailrec

object IsUnique {
  // Using Set
  var v_set: Set[Char] = Set()

  def verify(s: String): Boolean = {
    s.foreach { c =>
      if (v_set.contains(c)) {
        return false
      }
      v_set += c
    }
    true
  }

  //  Using sort
  def verify2(s: String): Boolean = {
    @tailrec
    def loop(in: List[Char]): Boolean = in match {
      case Nil => true
      case c :: cs => if (cs.nonEmpty && c == cs.head) false else loop(cs)
    }

    loop(s.sorted.toList)
  }

  // In-place
  def verify3(s: String): Boolean = ???

}
