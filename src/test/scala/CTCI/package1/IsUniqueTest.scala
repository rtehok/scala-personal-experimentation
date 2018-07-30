package CTCI.package1

import org.scalatest.{Matchers, FunSpec}

class IsUniqueTest extends FunSpec with Matchers {

  describe("String is over 128 char") {
    it("should return false") {
      IsUnique.isTooLong("a" * 129) shouldBe true
    }
  }

  describe("IsUnique") {
    describe("when parsing an empty string") {
      val s = ""
      it("should return true") {
        IsUnique.verify(s) shouldBe true
        IsUnique.verify2(s) shouldBe true
        IsUnique.verify3(s) shouldBe true
      }
    }

    describe("when parsing a string made of unique chars") {
      val s = "abcdefg"
      it("should return true") {
        IsUnique.verify(s) shouldBe true
        IsUnique.verify2(s) shouldBe true
        IsUnique.verify3(s) shouldBe true
      }
    }

    describe("when parsing a string made of not unique chars") {
      val s = "abadefg"
      it("should return false") {
        IsUnique.verify(s) shouldBe false
        IsUnique.verify2(s) shouldBe false
        IsUnique.verify3(s) shouldBe false
      }
    }
  }

}
