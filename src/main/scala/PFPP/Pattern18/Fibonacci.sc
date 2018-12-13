import scala.math.BigInt

lazy val fibs: Stream[BigInt] = BigInt(0) #:: BigInt(1) #:: fibs.zip(fibs.tail).map { n =>
  println("Adding %d and %d".format(n._1, n._2))
  n._1 + n._2
}


fibs take 5 foreach println
//0
//1
//Adding 0 and 1
//1
//Adding 1 and 1
//2
//Adding 1 and 2
//3
fibs take 6 foreach println
//0
//1
//1
//2
//3
//Adding 2 and 3
//5

//https://www.scala-lang.org/api/2.12.4/scala/collection/immutable/Stream.html