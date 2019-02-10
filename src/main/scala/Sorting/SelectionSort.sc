import scala.collection.mutable

def sort(arr: mutable.ArrayBuffer[Int]): mutable.ArrayBuffer[Int] = {
  def swap(i: Int, j: Int): Unit = {
    val t = arr(i)
    arr(i) = arr(j)
    arr(j) = t
  }

  for (i <- arr.indices) {
    var minimum_index = i
    for (j <- i + 1 until arr.length) {
      if (arr(j) < arr(minimum_index)) minimum_index = j
    }

    swap(i, minimum_index)
  }

  arr
}

assert(sort(mutable.ArrayBuffer[Int](5, 3, 6, 1, 2, 7, 4, 8)) == mutable.ArrayBuffer[Int](1, 2, 3, 4, 5, 6, 7, 8))