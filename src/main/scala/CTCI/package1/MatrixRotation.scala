package CTCI.package1

import scala.collection.mutable

object MatrixRotation {
  def go(matrix: mutable.ArrayBuffer[mutable.ArrayBuffer[Int]]): mutable.ArrayBuffer[mutable.ArrayBuffer[Int]] = {
    if (matrix.nonEmpty && matrix.length == matrix(0).length) {

      val n = matrix.length
      for (layer <- 0 until n / 2) {
        val first = layer
        val last = n - 1 - layer
        for (i <- first until last) {
          val offset = i - first

          val top = matrix(first)(i)
          matrix(first)(i) = matrix(last - offset)(first)
          matrix(last - offset)(first) = matrix(last)(last - offset)
          matrix(last)(last - offset) = matrix(i)(last)
          matrix(i)(last) = top

        }
      }

    }
    matrix
  }
}
