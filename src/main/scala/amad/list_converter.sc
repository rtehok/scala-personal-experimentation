import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

def fn(xs: List[Int]): List[(Int, Int)] = {
  val res = ListBuffer[(Int, Int)]()

  @tailrec
  def loop(start: Int, xs: List[Int]): Unit = {
    xs match {
      case Nil => ()
      case h :: Nil =>
        res.append((start, h))
      case h :: tail => if (tail.head == h + 1) {
        loop(start, tail)
      } else {
        res.append((start, h))
        loop(tail.head, tail)
      }
    }
  }

  if (xs.length == 1) {
    List((xs.head, xs.head))
  }
  else if (xs.length == 2) {
    List((xs.head, xs.last))
  }
  else {
    loop(xs.head, xs)
    res.toList
  }
}

fn(List(1)) == List((1, 1))
fn(List(1, 2)) == List((1, 2))
fn(List(5, 6, 7, 9, 12, 13, 15)) == List((5, 7), (9, 9), (12, 13), (15, 15))
fn(List(5, 6, 7, 9, 12, 13, 14, 15)) == List((5, 7), (9, 9), (12, 15))
fn(List(5, 6, 7, 9, 12, 13, 14, 15))