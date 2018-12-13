import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.util.Random

def testImmutable(count: Int): Seq[Int] = {
  var v = Vector[Int]()
  for (c <- Range(0, count)) {
    v = v :+ c
  }
  v
}

def testMutable(count: Int): Seq[Int] = {
  val s = ArrayBuffer[Int](count)
  for (c <- Range(0,count)) {
    s.append(c)
  }
  s.toIndexedSeq
}

def time[R](block: => R): R = {
  val start = System.nanoTime
  val result = block
  val end = System.nanoTime
  val elapsedTimeMs = (end - start) * 0.000001
  println("Elapsed time: %.3f msecs".format(elapsedTimeMs))
  result
}

def timeRuns[R](block: => R, count: Int) =
  for (_ <- Range(0, count)) time { block }

val oneMillion = 1000000
//timeRuns(testImmutable(oneMillion), 5)
//Elapsed time: 158.210 msecs
//Elapsed time: 62.463 msecs
//Elapsed time: 90.664 msecs
//Elapsed time: 78.911 msecs
//Elapsed time: 84.585 msecs

//timeRuns(testMutable(oneMillion), 5)
//Elapsed time: 34.015 msecs
//Elapsed time: 32.078 msecs
//Elapsed time: 35.915 msecs
//Elapsed time: 35.523 msecs
//Elapsed time: 31.743 msecs

case class Purchase(storeNumber: Int, customerNumber: Int, itemNumber: Int)

val r = new Random
def makeTestPurchase = Purchase(r.nextInt(100), r.nextInt(1000), r.nextInt(500))
def infiniteTestPurchases: Stream[Purchase] = makeTestPurchase #:: infiniteTestPurchases

def immutableSequenceEventProcessing(count: Int) = {
  val testPurchase = infiniteTestPurchases.take(count)
  var mapOfPurchases = Map[Int, List[Purchase]]()

  for(purchase <- testPurchase) {
    mapOfPurchases.get(purchase.storeNumber) match {
      case None => mapOfPurchases =
        mapOfPurchases + (purchase.storeNumber -> List(purchase))
      case Some(existingPurchase: List[Purchase]) => mapOfPurchases =
        mapOfPurchases + (purchase.storeNumber -> (purchase :: existingPurchase))
    }
  }
}

def mutableSequenceEventProcessing(count: Int) = {
  val testPurchase = infiniteTestPurchases.take(count)
  val mapOfPurchases = mutable.Map[Int, List[Purchase]]()

  for(purchase <- testPurchase) {
    mapOfPurchases.get(purchase.storeNumber) match {
      case None =>
        mapOfPurchases.put(purchase.storeNumber, List(purchase))
      case Some(existingPurchase: List[Purchase]) =>
        mapOfPurchases.put(purchase.storeNumber, purchase :: existingPurchase)
    }
  }

  mapOfPurchases.toMap
}

val fiveHundredThousand = 500000

timeRuns(immutableSequenceEventProcessing(fiveHundredThousand), 5)
//Elapsed time: 717.741 msecs
//Elapsed time: 620.083 msecs
//Elapsed time: 471.949 msecs
//Elapsed time: 170.614 msecs
//Elapsed time: 155.949 msecs

timeRuns(mutableSequenceEventProcessing(fiveHundredThousand), 5)
//Elapsed time: 132.206 msecs
//Elapsed time: 112.031 msecs
//Elapsed time: 127.914 msecs
//Elapsed time: 151.211 msecs
//Elapsed time: 214.439 msecs