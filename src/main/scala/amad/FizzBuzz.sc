def intToString(i: Int): Any = {
  val res1 = if (i % 3 == 0) "fizz" else ""
  val res2 = if (i % 5 == 0) "buzz" else ""
  if ((res1 + res2).isEmpty) i else res1 + res2
}

(0 to 100) map intToString
