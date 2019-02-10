import scala.collection.mutable.ArrayBuffer

def partition(arr: ArrayBuffer[Int], begin: Int, end: Int): Int = {
  def swap(i: Int, j: Int): Unit = {
    val t = arr(i)
    arr(i) = arr(j)
    arr(j) = t
  }

  var pivot = begin
  for (i <- begin + 1 to end) {
    if (arr(i) <= arr(begin)) {
      pivot = pivot + 1
      swap(i, pivot)
    }
  }
  swap(pivot, begin)

  pivot
}

def helper(arr: ArrayBuffer[Int], begin: Int, end: Int): Unit = {
  if (begin >= end) return

  val pivot = partition(arr, begin, end)
  helper(arr, begin, pivot - 1)
  helper(arr, pivot + 1, end)
}

def sort(arr: ArrayBuffer[Int], begin: Int = 0, end: Option[Int] = None): ArrayBuffer[Int] = {
  helper(arr, begin, end.getOrElse(arr.length - 1))
  arr
}

assert(sort(ArrayBuffer[Int](5, 3, 6, 1, 2, 7, 4, 8)) == ArrayBuffer[Int](1, 2, 3, 4, 5, 6, 7, 8))