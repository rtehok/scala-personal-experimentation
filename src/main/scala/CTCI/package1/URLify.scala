package CTCI.package1

object URLify {
  def go(str: String, trueLength: Int): String = {
    var spaceNb = 0
    val s_arr = str.toCharArray
    //Count the number of spaces
    for (i <- 0 until trueLength) {
      if (s_arr(i) == ' ') spaceNb = spaceNb + 1
    }
    var index = trueLength + spaceNb * 2

    //Check on length
    if (trueLength < str.length) s_arr(trueLength) = '\0'

    //Replace from end to start
    for (i <- trueLength - 1 to 0 by -1) {
      if (s_arr(i) == ' ') {
        s_arr(index - 1) = '0'
        s_arr(index - 2) = '2'
        s_arr(index - 3) = '%'
        index = index - 3
      } else {
        s_arr(index - 1) = s_arr(i)
        index = index - 1
      }
    }

    s_arr.mkString("")
  }
}
