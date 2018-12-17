def test[E](expression: E) = expression

test(println("Hello"))

def testTwice[E](expression: E) = {
  expression
  expression
}

testTwice(println("Hello"))

def testByName[E](expression: => E) = {
  expression
  expression
}

testByName(println("Hello"))

def timeRun[E](toTime: => E) = {
  val start = System.currentTimeMillis
  toTime
  System.currentTimeMillis - start
}

def avgTime[E](times: Int, toTime: => E) = {
  val allTimes = for (_ <- Range(0, times)) yield timeRun(toTime)
  allTimes.sum / times
}

val allTimes = for (_ <- Range(0, 5)) yield timeRun(Thread.sleep(1000))
//allTimes: scala.collection.immutable.IndexedSeq[Long] = Vector(1000, 1000, 1001, 1000, 1000)

avgTime(5, Thread.sleep(1000))
//res3: Long = 1000