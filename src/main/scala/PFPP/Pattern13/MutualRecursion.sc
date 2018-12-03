def isOdd(n: Int): Boolean = if (n == 0) false else isEven(n - 1)
def isEven(n: Int): Boolean = if (n == 0) true else isOdd(n - 1)

isEven(1000)
// isOdd(100001) ==> StackOverflowError

import scala.util.control.TailCalls._

def isOddTrampoline(n: Long): TailRec[Boolean] =
  if (n == 0) done(false) else tailcall(isEvenTrampoline(n - 1))

def isEvenTrampoline(n: Long): TailRec[Boolean] =
  if (n == 0) done(true) else tailcall(isOddTrampoline(n - 1))

isOddTrampoline(100001).result

class Transition
case object Ionization extends Transition
case object Deionization extends Transition
case object Vaporization extends Transition
case object Condensation extends Transition
case object Freezing extends Transition
case object Melting extends Transition
case object Sublimation extends Transition
case object Deposition extends Transition

def plasma(transitions: List[Transition]): TailRec[Boolean] =
  transitions match {
    case Nil => done(true)
    case Deionization :: t => tailcall(vapor(t))
    case _ => done(false)
  }

def vapor(transitions: List[Transition]): TailRec[Boolean] =
  transitions match {
    case Nil => done(true)
    case Condensation :: restTransitions => tailcall(liquid(restTransitions))
    case Deposition :: restTransitions => tailcall(solid(restTransitions))
    case Ionization :: restTransitions => tailcall(plasma(restTransitions))
    case _ => done(false)
  }

def liquid(transitions: List[Transition]): TailRec[Boolean] = transitions match {
  case Nil => done(true)
  case Vaporization :: restTransitions => tailcall(vapor(restTransitions))
  case Freezing :: restTransitions => tailcall(solid(restTransitions))
  case _ => done(false)
}
def solid(transitions: List[Transition]): TailRec[Boolean] = transitions match {
  case Nil => done(true)
  case Melting :: restTransitions => tailcall(liquid(restTransitions))
  case Sublimation :: restTransitions => tailcall(vapor(restTransitions))
  case _ => done(false)
}

// Will work without tailcall but not on Big List
val validSequence = List(Melting, Vaporization, Ionization, Deionization)
solid(validSequence).result

val invalidSequence = List(Vaporization, Freezing)
liquid(invalidSequence).result