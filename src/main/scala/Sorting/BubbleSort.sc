import scala.collection.mutable

def sort(arr: mutable.ListBuffer[Int]): mutable.ListBuffer[Int] = {
  def swap(i: Int, j: Int): Unit = {
    val t = arr(i)
    arr(i) = arr(j)
    arr(j) = t
  }

  var swapped = true
  val n = arr.length

  var x = -1
  while (swapped) {
    swapped = false

    x = x + 1
    for (i <- 1 until n - x) {
      if (arr(i - 1) > arr(i)) {
        swap(i - 1, i)
        swapped = true
      }
    }
  }

  arr
}

assert(sort(mutable.ListBuffer[Int](5, 3, 6, 1, 2, 7, 4, 8)) == mutable.ListBuffer[Int](1, 2, 3, 4, 5, 6, 7, 8))