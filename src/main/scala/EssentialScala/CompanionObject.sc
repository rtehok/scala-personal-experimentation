class Person(val first: String, val last: String){}

object Person {
  def apply(s: String) = {
    val parts = s.split(" ")
    new Person(parts.head, parts(1))
  }
}

val p = Person("John Smith")
p.first
p.last

