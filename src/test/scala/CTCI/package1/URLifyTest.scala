package CTCI.package1

import org.scalatest.{FunSpec, Matchers}

class URLifyTest extends FunSpec with Matchers {

  describe("URLify.go") {
    it("should work") {
      URLify.go("Mr John Smith    ", 13) shouldEqual "Mr%20John%20Smith"
      URLify.go("Mr  John Smith      ", 14) shouldEqual "Mr%20%20John%20Smith"
      URLify.go("Mr John Smith    ", 12) shouldEqual "Mr%20John%20Smit "
    }
  }

}
