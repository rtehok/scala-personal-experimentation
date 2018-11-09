val isVowel = Set('a', 'e', 'i', 'o', 'u')
def vowelsInWord(word: String) = word.filter(isVowel).toSet

vowelsInWord("onomotopeia")
vowelsInWord("yak")

def prependHello(names: Seq[String]) =
  names.map(name => "Hello " + name)

prependHello(Vector("Mike", "John", "Joe"))

//Description of .sum
def sumSequence(sequence: Seq[Int]) =
  if (sequence.isEmpty) 0 else sequence.reduce((acc, curr) => acc + curr)

sumSequence(Vector(1, 2, 3, 4, 5))


case class Person(name: String, address: Address)

case class Address(zip: Int)

def generateGreetings(people: Seq[Person]): Seq[String] =
  for (Person(name, address) <- people if isCloseZip(address.zip))
    yield s"Hello, $name, and welcome here"

def isCloseZip(zipCode: Int) = zipCode == 19123 || zipCode == 19103

def printGreetings(people: Seq[Person]): Unit =
  for (Person(name, address) <- people if isCloseZip(address.zip))
    println(s"Hello, $name, and welcome here")

val people = Vector(Person("toto", Address(19123)),
  Person("tata", Address(19103)),
  Person("titi", Address(75013))
)

generateGreetings(people)
//res4: Seq[String] = Vector(Hello, toto, and welcome here, Hello, tata, and welcome here)
printGreetings(people)
//Hello, toto, and welcome here
//Hello, tata, and welcome here
//res5: Unit = ()