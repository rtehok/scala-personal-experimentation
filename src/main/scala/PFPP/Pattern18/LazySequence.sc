def pagedSequence(pageNum: Int) : Stream[String] =
  getPage(pageNum) match {
    case Some(page: String) => page #:: pagedSequence(pageNum + 1)
    case _ => Stream.empty
  }


def getPage(page: Int): Option[String] =
  page match {
    case 1 => Some("Page1")
    case 2 => Some("Page2")
    case 3 => Some("Page3")
    case 4 => None
  }


pagedSequence(1) take 2 force
//res0: scala.collection.immutable.Stream[String] = Stream(Page1, Page2)

pagedSequence(1) force
//res1: scala.collection.immutable.Stream[String] = Stream(Page1, Page2, Page3)


val holdsHead = {
  def pagedSequence(pageNum: Int): Stream[String] =
    getPage(pageNum) match {
      case Some(page: String) => {
        println("Realizing " + page)
        page #:: pagedSequence(pageNum + 1)
      }
      case None => Stream.Empty
    }
  pagedSequence(1)
}

holdsHead force
//Realizing Page2
//Realizing Page3
//res2: scala.collection.immutable.Stream[String] = Stream(Page1, Page2, Page3)

holdsHead force
//res3: scala.collection.immutable.Stream[String] = Stream(Page1, Page2, Page3)

def doesntHoldHead = {
  def pagedSequence(pageNum: Int): Stream[String] =
    getPage(pageNum) match {
      case Some(page: String) => {
        println("Realizing " + page)
        page #:: pagedSequence(pageNum + 1)
      }
      case None => Stream.Empty
    }
  pagedSequence(1)
}

doesntHoldHead force
//Realizing Page1
//Realizing Page2
//Realizing Page3
//res4: scala.collection.immutable.Stream[String] = Stream(Page1, Page2, Page3)

doesntHoldHead force
//Realizing Page1
//Realizing Page2
//Realizing Page3
//res5: scala.collection.immutable.Stream[String] = Stream(Page1, Page2, Page3)