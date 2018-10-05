package CTCI.package1

import scala.collection.mutable

class ZeroMatrixTest extends org.scalatest.FunSuite {

  test("go should not change") {
    val matrix = mutable.ArrayBuffer(mutable.ArrayBuffer(1,2,3,4),mutable.ArrayBuffer(5,6,7,8),mutable.ArrayBuffer(9,10,11,12),mutable.ArrayBuffer(13,14,15,16))
    assert(ZeroMatrix.go(matrix) === matrix)
  }

  test("go should change") {
    val matrix = mutable.ArrayBuffer(mutable.ArrayBuffer(1,2,3,4),mutable.ArrayBuffer(5,6,0,8),mutable.ArrayBuffer(9,10,11,12),mutable.ArrayBuffer(13,14,15,16),mutable.ArrayBuffer(13,14,15,16))
    val res = mutable.ArrayBuffer(mutable.ArrayBuffer(1,2,0,4),mutable.ArrayBuffer(0,0,0,0),mutable.ArrayBuffer(9,10,0,12),mutable.ArrayBuffer(13,14,0,16),mutable.ArrayBuffer(13,14,0,16))
    assert(ZeroMatrix.go(matrix) === res)
  }

}
