case class PersonCaseClass(firstName: String, middleName: String = "", lastName: String)

val p1 = PersonCaseClass(firstName = "John", lastName = "Adams")
val p2 = PersonCaseClass(firstName = "John", lastName = "Adams")

p1.equals(p2)

val p3 = PersonCaseClass(firstName = "John", middleName = "Quincy", lastName = "Adams")

p2.equals(p3)

val p2Bis = p1.copy(middleName = "Quincy")

p3 match {
  case PersonCaseClass(f, m, l) =>
    "First: %s - Middle: %s - Last: %s".format(f, m, l)
}
//res2: String = First: John - Middle: Quincy - Last: Adams

def p: (String, String) = ("John", "Adams")

p match {
  case (f, l) =>
    println("First name is: " + f)
    println("Last name is: " + l)
}
//First name is: John
//Last name is: Adams
//res3: Unit = ()

