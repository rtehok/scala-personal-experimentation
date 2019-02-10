import scala.collection.mutable

def mergeSort(arr: mutable.ArrayBuffer[Int]): List[Int] = {
  if (arr.length <= 1) return arr.toList

  val mid: Int = Math.round(arr.length / 2)

  val (left, right) = (mergeSort(arr.splitAt(mid)._1), mergeSort(arr.splitAt(mid)._2))

  merge(left, right, arr)
}

def merge(left: List[Int], right: List[Int], res: mutable.ArrayBuffer[Int]): List[Int] = {
  var i = 0
  var j = 0

  while (i < left.length && j < right.length) {
    if (left(i) < right(j)) {
      res(i + j) = left(i)
      i = i + 1
    }
    else {
      res(i + j) = right(j)
      j = j + 1
    }
  }

  for (i <- i until left.length) {
    res(i + j) = left(i)
  }

  for (j <- j until right.length) {
    res(i + j) = right(j)
  }

  res.toList
}

assert(mergeSort(mutable.ArrayBuffer(5, 3, 6, 1, 2, 7, 4, 8)) == List(1, 2, 3, 4, 5, 6, 7, 8))