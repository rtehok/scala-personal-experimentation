def makeLogger(fn: (Int, Int) => Int) =
  (a: Int, b: Int) => {
    val res = fn(a,b)
    println(s"Result is: $res")
    res
  }

def add(a:Int, b: Int) = a + b
def sub(a:Int, b: Int) = a - b
def div(a:Int, b: Int) = a / b
def mul(a:Int, b: Int) = a * b

val loggingAdd = makeLogger(add)
val loggingSub = makeLogger(sub)
val loggingDib = makeLogger(div)
val loggingMul = makeLogger(mul)

loggingAdd(3,2)