package CTCI.package1

object PalindromePermutation {

  def go(str: String): Boolean = {
    def checkMaxOneOdd(ints: Array[Int]): Boolean = {
      var foundOdd = false
      for (i <- ints) {
        if(i % 2 == 1) {
          if (foundOdd) return false
          foundOdd = true
        }
      }
      foundOdd
    }

    def getCharNumber(c: Char): Int = {
      val a = 'a'.getNumericValue
      val value = c.getNumericValue

      if (a <= value && value <= 'z'.getNumericValue) value - a
      else -1
    }

    def buildCharFrequency(str: String): Array[Int] = {
      val res = new Array[Int]('z'.getNumericValue - 'a'.getNumericValue + 1)
      for (c <- str) {
        val value = getCharNumber(c)
        if(value != -1) res(value) += 1
      }
      res
    }

    checkMaxOneOdd(buildCharFrequency(str))

  }

}
