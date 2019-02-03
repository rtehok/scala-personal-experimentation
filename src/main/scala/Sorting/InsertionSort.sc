import scala.collection.mutable

def sort(arr: mutable.ListBuffer[Int]): mutable.ListBuffer[Int] = {

  for (i <- arr.indices) {
    val cursor = arr(i)
    var pos = i

    while (pos > 0 && arr(pos - 1) > cursor) {
      arr(pos) = arr(pos - 1)
      pos = pos - 1
    }

    arr(pos) = cursor
  }


  arr
}

assert(sort(mutable.ListBuffer[Int](5, 3, 6, 1, 2, 7, 4, 8)) == mutable.ListBuffer[Int](1, 2, 3, 4, 5, 6, 7, 8))