package CTCI.package1

import org.scalatest.{FunSpec, Matchers}

class CheckPermutationTest extends FunSpec with Matchers {

  describe("CheckPermutation") {
    it("verifies") {
      CheckPermutation.verify("","a") shouldBe false
      CheckPermutation.verify("  ","a") shouldBe false
      CheckPermutation.verify("  "," ") shouldBe true
      CheckPermutation.verify("aa","a") shouldBe false
      CheckPermutation.verify("aa","a a") shouldBe true
      CheckPermutation.verify("ab","ba") shouldBe true
      CheckPermutation.verify("Dog","God") shouldBe true
    }

    it("verifies 2") {
      CheckPermutation.verify2("","a") shouldBe false
      CheckPermutation.verify2("  ","a") shouldBe false
      CheckPermutation.verify2("  "," ") shouldBe false
      CheckPermutation.verify2("aa","a") shouldBe false
      CheckPermutation.verify2("aa","a a") shouldBe false
      CheckPermutation.verify2("ab","ba") shouldBe true
      CheckPermutation.verify2("Dog","God") shouldBe false
      CheckPermutation.verify2("dog","god") shouldBe true
    }
  }

}
