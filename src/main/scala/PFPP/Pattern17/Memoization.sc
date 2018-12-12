def expensiveLookup(id: Int): Option[String] = {
  Thread.sleep(1000)
  println(s"Expensive lookup for $id")
  Map(42 -> "foo", 12 -> "bar", 1 -> "baz").get(id)
}

def memoizedLookup(): Int => Option[String] = {
  var cache = Map[Int, Option[String]]()
  id: Int =>
    cache.get(id) match {
      case Some(res: Option[String]) => res
      case None => {
        val result = expensiveLookup(id)
        cache += id -> result
        result
      }
  }
}


val memoizedLookup1 = memoizedLookup()
val memoizedLookup2 = memoizedLookup()

memoizedLookup1(42)
//Expensive lookup for 42
//res0: Option[String] = Some(foo)

memoizedLookup1(42)
//res1: Option[String] = Some(foo)

memoizedLookup2(42)
//Expensive lookup for 42
//res2: Option[String] = Some(foo)