package CTCI.package1

import scala.collection.mutable

object ZeroMatrix {
  def go(matrix: mutable.ArrayBuffer[mutable.ArrayBuffer[Int]]): mutable.ArrayBuffer[mutable.ArrayBuffer[Int]] = {
    val row = mutable.ArrayBuffer.fill(matrix.size)(false)
    val column = mutable.ArrayBuffer.fill(matrix(0).size)(false)

    matrix.zipWithIndex.foreach { case (matrix_row, i) =>
      matrix_row.zipWithIndex.foreach {  case (v, j) =>
        if (v == 0) {
          row(i) = true
          column(j) = true
        }
      }
    }

  def nullifyRow(m: mutable.ArrayBuffer[mutable.ArrayBuffer[Int]], row: Int): Unit = {
    m(row) = mutable.ArrayBuffer.fill(column.size)(0)
  }

  def nullifyColumn(m: mutable.ArrayBuffer[mutable.ArrayBuffer[Int]], col: Int): Unit = {
    m.foreach {
      _.update(col, 0)
    }
  }

  for {
    i <- matrix.indices if row(i)
  } nullifyRow(matrix, i)

  for {
    j <- matrix(0).indices if column(j)
  } nullifyColumn(matrix, j)

  matrix
}

}
