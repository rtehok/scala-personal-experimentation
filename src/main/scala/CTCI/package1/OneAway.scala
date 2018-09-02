package CTCI.package1

object OneAway {
  def go(s1: String, s2: String): Boolean = {
    if (Math.abs(s1.length - s2.length) > 1) return false

    if (s1.length <= s2.length) {
      compare(s1.toCharArray, s2.toCharArray)
    } else {
      compare(s2.toCharArray, s1.toCharArray)
    }

  }

  def compare(arr1: Array[Char], arr2: Array[Char]): Boolean = {
    var i = 0
    var changes = 0

    for (c <- arr1) {
      if (c != arr2(i)) changes = changes + 1

      if (changes > 1) return false

      i = i + 1
    }

    changes <= 1
  }
}
