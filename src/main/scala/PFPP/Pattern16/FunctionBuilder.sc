def discount(percent: Double): Double => Double = {
  if (percent < 0 || percent >= 100) throw new IllegalArgumentException("Must be between 0 and 100")
  originalPrice: Double => originalPrice * (1 - percent / 100)
}

val twentyFivePercentOff = discount(25)

(Vector(100.0, 25.0, 50.0, 25.0) map twentyFivePercentOff).sum


import scala.annotation.tailrec

def selector(path: Symbol*): Map[Symbol, Any] => Option[Any] = {
  if (path.isEmpty) throw new IllegalArgumentException("path must not be empty")

  @tailrec
  def helper(pathSeq: Seq[Symbol], ds: Map[Symbol, Any]): Option[Any] = {
    if (pathSeq.size == 1) {
      ds.get(pathSeq.head)
    } else {
      ds.get(pathSeq.head) match {
        case Some(currentMap: Map[Symbol, Any]@unchecked) => helper(pathSeq.tail, currentMap)
        case _ => None
      }
    }
  }

  ds: Map[Symbol, Any] => helper(path.toSeq, ds)
}

val simplePerson = Map('name -> "Michael Bevilacqua-Linn")
val name = selector('name)
name(simplePerson)


val moreComplexPerson = Map('name -> Map('first -> "Michael", 'last -> "Bevilacqua-Linn"))
val firstName = selector('name, 'first)
firstName(moreComplexPerson)

val middleName = selector('name, 'middle)
middleName(moreComplexPerson)

val appendA = (s: String) => s + "a"
val appendB = (s: String) => s + "b"
val appendC = (s: String) => s + "c"

val appendCBA = appendA compose appendB compose appendC

appendCBA("z")

case class HttpRequest(
                        headers: Map[String, String],
                        payload: String,
                        principal: Option[String] = None
                      )


def checkAutorization(request: HttpRequest): HttpRequest = {
  val authHeader = request.headers.get("Authorization")
  val mockPrincipal = authHeader match {
    case Some(_) => Some("AUser")
    case _ => None
  }
  request.copy(principal = mockPrincipal)
}

def logFingerPrint(request: HttpRequest): HttpRequest = {
  val fingerPrint = request.headers.getOrElse("X-RequestFingerPrint", "")
  println("FINGERPRINT=" + fingerPrint)
  request
}

def composeFilters(filters: Seq[HttpRequest => HttpRequest]): HttpRequest => HttpRequest =
  filters.reduce {
    (allFilters, currentFilter) => allFilters compose currentFilter
  }

val filters = Vector(checkAutorization(_), logFingerPrint(_))

val filterChains = composeFilters(filters)

val requestHeaders = Map("Authorization" -> "Auth", "X-RequestFingerPrint" -> "fingerprint")
val request = HttpRequest(requestHeaders, "body")

filterChains(request)
//FINGERPRINT=fingerprint
//res5: HttpRequest = HttpRequest(Map(Authorization -> Auth, X-RequestFingerPrint -> fingerprint),body,Some(AUser))

val requestHeaders2 = Map("Auth" -> "Auth", "X-RequestFingerPrint" -> "fingerprint")
val request2 = HttpRequest(requestHeaders2, "body")
filterChains(request2)
//FINGERPRINT=fingerprint
//res6: HttpRequest = HttpRequest(Map(Auth -> Auth, X-RequestFingerPrint -> fingerprint),body,None)

val requestHeaders3 = Map("Authorization" -> "Auth", "X-Request" -> "fingerprint")
val request3 = HttpRequest(requestHeaders3, "body")
filterChains(request3)
//FINGERPRINT=
//res7: HttpRequest = HttpRequest(Map(Authorization -> Auth, X-Request -> fingerprint),body,Some(AUser))