case class Person(firstName: String = "John", lastName: String = "Doe")

val nullPerson = Person()
def fetchPerson(people: Map[Int, Person], id: Int): Person = people.getOrElse(id, nullPerson)

val joe = Person("Joe", "Smith")
val jack = Person("Jack", "Brown")
val somePeople = Map(1 -> joe, 2 -> jack)

fetchPerson(somePeople, 1)
//res0: Person = Person(Joe,Smith)
fetchPerson(somePeople, 3)
//res1: Person = Person(John,Doe)

def vecFoo = Vector("foo")
def someFoo = Some("foo")
def someBar = Some("bar")
def aNone = None

for (theFoo <- vecFoo) yield theFoo
//res2: scala.collection.immutable.Vector[String] = Vector(foo)

for (theFoo <- someFoo) yield theFoo
//res3: Option[String] = Some(foo)

for {
  theFoo <- someFoo
  theBar <- someBar
}
  yield (theFoo, theBar)
//res4: Option[(String, String)] = Some((foo,bar))

for (theFoo <- someFoo; theNone <- aNone) yield (theFoo, theNone)
//res5: Option[(String, Nothing)] = None

def buildPerson(mayBeFirstName: Option[String], mayBeLastName: Option[String]) =
  (for {
    firstName <- mayBeFirstName
    lastName <- mayBeLastName
  } yield Person(firstName, lastName)).getOrElse(nullPerson)

buildPerson(Some("Mike"), Some("Linn"))
//res6: Person = Person(Mike,Linn)
buildPerson(Some("Mike"), None)
//res7: Person = Person(John,Doe)


