package CTCI.package1

import org.scalatest.{FunSpec, Matchers}

class PalindromePermutationTest extends FunSpec with Matchers {

  describe("PalindromePermutation") {
    it("should work") {
      PalindromePermutation.go("Tact Cao") shouldBe true
      PalindromePermutation.go("Tact Caoo") shouldBe false
      PalindromePermutation.go("Tact Caooo") shouldBe true
      PalindromePermutation.go("Tact Caooop") shouldBe false
      PalindromePermutation.go("Tact Caoooop") shouldBe true
      PalindromePermutation.go("zazaz") shouldBe true
      PalindromePermutation.go("zaza") shouldBe false
    }
  }

}
