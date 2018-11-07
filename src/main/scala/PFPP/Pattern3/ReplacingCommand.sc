class CashRegister(var total: Int) {
  def addCash(toAdd: Int): Unit = {
    total += toAdd
  }
}

def makePurchase(register: CashRegister, amount: Int): () => Unit = {
  () => {
    println("Purchase in amount: " + amount)
    register.addCash(amount)
  }
}

var purchases: Vector[() => Unit] = Vector()
def executePurchase(purchase: () => Unit): Unit = {
  purchases = purchases :+ purchase
  purchase()
}

val register = new CashRegister(0)
val purchase1 = makePurchase(register, 100)
val purchase2 = makePurchase(register, 50)

executePurchase(purchase1)
executePurchase(purchase2)

register.total
// res2: Int = 150

register.total = 0

for(p <- purchases){ p.apply() }

register.total
//res4: Int = 150