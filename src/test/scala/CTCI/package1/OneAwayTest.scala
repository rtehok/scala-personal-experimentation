package scala.CTCI.package1

import CTCI.package1.OneAway
import org.scalatest.{FunSpec, Matchers}

class OneAwayTest extends FunSpec with Matchers {

  describe("go") {
    it("should return true") {
      OneAway.go("toto", "toto") shouldBe true
      OneAway.go("toto", "tot") shouldBe true
      OneAway.go("toto", "tota") shouldBe true
      OneAway.go("tot", "tota") shouldBe true
    }

    it("should return false") {
      OneAway.go("toto", "to") shouldBe false
      OneAway.go("toto", "tata") shouldBe false
      OneAway.go("to", "tata") shouldBe false
    }
  }

}
