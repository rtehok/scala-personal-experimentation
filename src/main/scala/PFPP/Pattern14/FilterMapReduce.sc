def calculateDiscount(prices: Seq[Double]): Double = {
  (prices filter (_ >= 20) map (_ * 0.1)).sum
}

calculateDiscount(Vector(20.0, 4.5, 50.0, 15.75, 30.0, 3.5))

