
def solveRPN(input: String): Double =
  input.split(" ").toList.foldLeft(List[Double]()) {
    (acc, token) =>
      (acc, token) match {
        case (x :: y :: zs, "*") => (x * y) :: zs
        case (x :: y :: zs, "+") => (x + y) :: zs
        case (x :: y :: zs, "-") => (y - x) :: zs
        case (x :: y :: zs, "/") => (y / x) :: zs
        case (x :: y :: zs, "^") => Math.pow(y, x) :: zs
        case (x :: xs, "ln") => Math.log(x) :: xs
        case (xs, "sum") => List(xs.sum)
        case (_, _) => token.toDouble :: acc
      }
  }.head


assert(solveRPN("4 2 * 8 + 2 /") == 8)
assert(solveRPN("90 34 12 33 55 66 + * - + -") == 4037)
assert(solveRPN("10 10 10 10 10 sum 4 /") == 12.5)
assert(solveRPN("10 2 ^") == 100)