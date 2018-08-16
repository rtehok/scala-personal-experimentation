//import com.sun.xml.internal.fastinfoset.util.CharArray
//
//case class Person(name: String,
//                  isMale: Boolean,
//                  children: Person*)
//
//val lara = Person("Lara", false)
//val bob = Person("Bob", true)
//val julie = Person("Julie", false, lara, bob)
//val persons = List(lara, bob, julie)
//
//persons filter (p => !p.isMale) flatMap (p =>
//  p.children map (c => (p.name, c.name))
//  )
//
//for {
//  p <- persons if !p.isMale
//  c <- p.children
//} yield (p.name, c.name)
//
//
//val s1 = "Dog"
//val s11 = s1.sorted.toLowerCase.trim
//val s12 = s1.toLowerCase.sorted.trim
//s11 == s12
//
//val s2 = "God"
//val s21 = s2.sorted.toLowerCase.trim
//val s22 = s2.toLowerCase.sorted.trim
//s21 == s22
//
//s11 == s21
//s11 == s22
//
//
//val arr = Array[Int](4)
//arr(2) = 2

val char_arr = "ab".toCharArray
char_arr(0) = 'c'
char_arr.mkString("")
