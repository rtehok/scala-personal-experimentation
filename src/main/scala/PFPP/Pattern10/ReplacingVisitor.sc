///////////
// implicit
///////////

trait Person {
  def fullName: String

  def firstName: String

  def lastName: String

  def houseNum: Int

  def street: String
}

class SimplePerson(val firstName: String, val lastName: String,
                   val houseNum: Int, val street: String) extends Person {
  def fullName = firstName + " " + lastName
}

val simplePerson = new SimplePerson("Mike", "Linn", 123, "Fake. St.")

simplePerson.fullName

implicit class ExtendedPerson(person: Person) {
  def fullAddress = person.houseNum + " " + person.street
}

simplePerson.fullAddress

class ComplexPerson(name: Name, address: Address) extends Person {
  def fullName = name.firstName + " " + name.lastName

  def firstName = name.firstName

  def lastName = name.lastName

  def houseNum = address.houseNum

  def street = address.street
}

class Address(val houseNum: Int, val street: String)

class Name(val firstName: String, val lastName: String)

val name = new Name("Mike", "Linn")
val address = new Address(123, "Fake St.")
val complexPerson = new ComplexPerson(name, address)

///////////////
// Polymorphism
///////////////

trait PerimeterShapes {

  trait Shape {
    def perimeter: Double
  }

  class Circle(radius: Double) extends Shape {
    def perimeter = 2 * Math.PI * radius
  }

  class Rectangle(width: Double, height: Double) extends Shape {
    def perimeter = 2 * width + 2 * height
  }

}

object FirstShapeExample extends PerimeterShapes {
  val aCircle = new Circle(4)
  val aRectangle = new Rectangle(2, 2)
}

import FirstShapeExample._

aCircle.perimeter
aRectangle.perimeter

trait AreaShapes extends PerimeterShapes {

  trait Shape extends super.Shape {
    def area: Double
  }

  class Circle(radius: Double) extends super.Circle(radius) with Shape {
    def area = Math.PI * radius * radius
  }

  class Rectangle(width: Double, height: Double)
    extends super.Rectangle(width, height) with Shape {
    def area = width * height
  }

}

object SecondShapeExample extends AreaShapes {
  val someShapes = Vector(new Circle(4), new Rectangle(2, 2))
}


for (shape <- SecondShapeExample.someShapes) yield shape.perimeter
//res4: scala.collection.immutable.Vector[Double] = Vector(25.132741228718345, 8.0)

for (shape <- SecondShapeExample.someShapes) yield shape.area

//res5: scala.collection.immutable.Vector[Double] = Vector(50.26548245743669, 4.0)

trait MorePerimeterShapes extends PerimeterShapes {

  class Square(side: Double) extends Shape {
    def perimeter = 4 * side
  }

}

trait MoreAreaShapes extends AreaShapes with MorePerimeterShapes {

  class Square(side: Double) extends super.Square(side) with Shape {
    def area = side * side
  }

}

object ThirdShapeExample extends MoreAreaShapes {
  val someMoreShapes = Vector(new Circle(4), new Rectangle(2, 2), new Square(4))
}

for(shape <- ThirdShapeExample.someMoreShapes) yield shape.perimeter
//res6: scala.collection.immutable.Vector[Double] = Vector(25.132741228718345, 8.0, 16.0)

for(shape <- ThirdShapeExample.someMoreShapes) yield shape.area
//res7: scala.collection.immutable.Vector[Double] = Vector(50.26548245743669, 4.0, 16.0)