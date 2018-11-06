case class Person(firstName: String, middleName: String, lastName: String)

val p1 = Person("Aaron", "Jeffrey", "Smith")
val p2 = Person("Aaron", "Bailey", "Zanthar")
val p3 = Person("Brian", "Adams", "Smith")
val people = Vector(p2, p3, p1)

def complicatedCompare(p1: Person, p2: Person): Boolean =
  if(p1.firstName != p2.firstName) p1.firstName < p2.firstName
  else if (p1.lastName != p2.lastName) p1.lastName < p2.lastName
  else p1.middleName < p2.middleName

people.sortWith(complicatedCompare)
//res0: scala.collection.immutable.Vector[Person] = Vector(Person(Aaron,Jeffrey,Smith), Person(Aaron,Bailey,Zanthar), Person(Brian,Adams,Smith))