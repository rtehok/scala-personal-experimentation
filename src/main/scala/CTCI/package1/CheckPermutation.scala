package CTCI.package1

object CheckPermutation {

  def hasSameSize(s1: String, s2: String): Boolean = s1.length == s2.length

  // Using sort O(n log(n))
  def verify(s1: String, s2: String): Boolean = {
    val s1a = s1.toLowerCase.sorted.trim
    val s2a = s2.toLowerCase.sorted.trim
    if (!hasSameSize(s1a, s2a)) return false

    s1a.equals(s2a)
  }

  // Whitespace and case sensitive
  def verify2(s1: String, s2: String): Boolean = {
    if (!hasSameSize(s1, s2)) return false

    val s_array = Array.fill(128){0}

    s1.toList.foreach { c =>
      s_array(c) = s_array(c) + 1
    }

    s2.toList.foreach{ c =>
      s_array(c) = s_array(c) - 1
      if (s_array(c) < 0) return false
    }

    true
  }

}
