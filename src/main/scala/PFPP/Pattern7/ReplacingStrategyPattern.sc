case class Person(firstName: Option[String], middleName: Option[String], lastName: Option[String])

def isValidFirstName(person: Person): Boolean = person.firstName.isDefined
def isValidFullName(person: Person): Boolean = person match {
  case Person(f, m, l) => f.isDefined && m.isDefined && l.isDefined
}

def personCollector(isValid: Person => Boolean): Person => Vector[Person] = {
  var validPeople = Vector[Person]()
  person: Person => {
    if (isValid(person)) validPeople = validPeople :+ person
    validPeople
  }
}

val firstNameValidator = personCollector(isValidFirstName)
val fullNameValidator = personCollector(isValidFullName)

val p1 = Person(Some("John"), Some("Quincy"), Some("Adams"))
val p2 = Person(Some("Mike"), None, Some("Linn"))
val p3 = Person(None, None, None)

firstNameValidator(p1)
firstNameValidator(p2)
firstNameValidator(p3)

//res0: Vector[Person] = Vector(Person(Some(John),Some(Quincy),Some(Adams)))
//res1: Vector[Person] = Vector(Person(Some(John),Some(Quincy),Some(Adams)), Person(Some(Mike),None,Some(Linn)))
//res2: Vector[Person] = Vector(Person(Some(John),Some(Quincy),Some(Adams)), Person(Some(Mike),None,Some(Linn)))

fullNameValidator(p1)
fullNameValidator(p2)
fullNameValidator(p3)

//res3: Vector[Person] = Vector(Person(Some(John),Some(Quincy),Some(Adams)))
//res4: Vector[Person] = Vector(Person(Some(John),Some(Quincy),Some(Adams)))
//res5: Vector[Person] = Vector(Person(Some(John),Some(Quincy),Some(Adams)))