package CTCI.package1

import org.scalatest.{FunSpec, Matchers}

import scala.collection.mutable

class MatrixRotationTest extends FunSpec with Matchers {

  describe("go") {
    it("should return true") {

      val matrix = mutable.ArrayBuffer(mutable.ArrayBuffer(1,2,3,4),mutable.ArrayBuffer(5,6,7,8),mutable.ArrayBuffer(9,10,11,12),mutable.ArrayBuffer(13,14,15,16))
      val result = mutable.ArrayBuffer(mutable.ArrayBuffer(13,9,5,1),mutable.ArrayBuffer(14,10,6,2),mutable.ArrayBuffer(15,11,7,3),mutable.ArrayBuffer(16,12,8,4))
      MatrixRotation.go(matrix) shouldEqual result
    }
  }

}
