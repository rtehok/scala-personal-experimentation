package CTCI.package1

import scala.annotation.tailrec

object IsUnique {
  def isTooLong(s: String): Boolean = s.length > 128

  // Using Set O(n)
  var v_set: Set[Char] = Set()

  def verify(s: String): Boolean = {
    if(isTooLong(s)) return false

    s.foreach { c =>
      if (v_set.contains(c)) {
        return false
      }
      v_set += c
    }
    true
  }

  //  Using sort O(n log(n))
  def verify2(s: String): Boolean = {
    if(isTooLong(s)) return false

    @tailrec
    def loop(in: List[Char]): Boolean = in match {
      case Nil => true
      case c :: cs => if (cs.nonEmpty && c == cs.head) false else loop(cs)
    }

    loop(s.sorted.toList)
  }

  // In-place O(n2)
  def verify3(s: String): Boolean = {
    if(isTooLong(s)) return false

    @tailrec
    def loop(in: List[Char]): Boolean = in match {
      case Nil => true
      case c :: cs => if (cs.contains(c)) false else loop(cs)
    }

    loop(s.toList)
  }

}
