package CTCI.package1

import org.scalatest.{FunSpec, Matchers}

class StringCompressionTest extends FunSpec with Matchers {

  describe("StringCompression") {
    it("should work") {
      StringCompression.go("aabcccccaaa") shouldBe "a2b1c5a3"
      StringCompression.go("abcccccaaa") shouldBe "a1b1c5a3"
      StringCompression.go("aabcccccaab") shouldBe "a2b1c5a2b1"
      StringCompression.go("bbbbbaabcccccaabc") shouldBe "b5a2b1c5a2b1c1"
      StringCompression.go("aabbccddeeffgg") shouldBe "aabbccddeeffgg"
      StringCompression.go("aabbccddeeffgghhh") shouldBe "a2b2c2d2e2f2g2h3"
      StringCompression.go("abcdef") shouldBe "abcdef"
    }
  }

}