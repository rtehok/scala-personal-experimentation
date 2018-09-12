package CTCI.package1

import scala.collection.mutable.ArrayBuffer

object StringCompression {
  def go(s: String): String = {
    val s_arr = s.toCharArray
    val res = new ArrayBuffer[(Char, Int)]()
    var j = 0

    res.append((s_arr(j), 1))

    s.zipWithIndex.foreach {
      case (c, i) =>
        if (i + 1 <= s_arr.length - 1) {
          val next = s_arr(i + 1)
          if (c == next) res.update(j, (c, res(j)._2 + 1))
          else {
            res.append((next, 1))
            j = j + 1
          }
        }
    }

    val result = res.map { case (c, i) => s"$c$i" }.mkString

    if (result.length < s.length) result else s
  }

}
