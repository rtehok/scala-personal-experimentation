import scala.annotation.tailrec

def sumRecursive(upTo: Int): Int =
  if(upTo == 0) 0
  else upTo + sumRecursive(upTo -1)

@tailrec
def sumTailRecursive(upTo: Int, currentSum: Int = 0): Int =
  if (upTo == 0) currentSum
  else sumTailRecursive(upTo - 1, upTo + currentSum)


val s1 = sumRecursive(9999)
// upTo 10000, then StackOverFlow error

val s2 = sumTailRecursive(10001)

case class Person(firstNames: String, lastNames: String)

def makePeople(firstNames: Seq[String], lastNames: Seq[String]) = {
  @tailrec
  def helper(firstNames: Seq[String], lastNames: Seq[String], persons: Vector[Person]): Vector[Person] =
    if (firstNames.isEmpty) persons
    else helper(firstNames.tail, lastNames.tail, persons :+ Person(firstNames.head, lastNames.head))

  helper(firstNames, lastNames, Vector[Person]())
}

val firstNames = Vector("John", "Jane")
val lastNames = Vector("Boy", "Girl")

makePeople(firstNames, lastNames)