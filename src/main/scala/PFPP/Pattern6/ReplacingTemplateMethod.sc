def makeGradeReporter(
                       numToLetter: Double => String,
                       printGradeReport: Seq[String] => Unit): Seq[Double] => Unit =
  (grades: Seq[Double]) => printGradeReport(grades.map(numToLetter))

def fullGradeConverter(grade: Double): String =
  if (grade <= 5.0 && grade > 4.0) "A"
  else if (grade <= 4.0 && grade > 3.0) "B"
  else if (grade <= 3.0 && grade > 2.0) "C"
  else if (grade <= 2.0 && grade > 0.0) "D"
  else if (grade == 0.0) "F"
  else "N/A"

def printHistogram(grade: Seq[String]): Unit = {
  val grouped: Map[String, Seq[String]] = grade.groupBy(identity)
  val counts: Seq[(String, Int)] = grouped.map { case (k, v) => (k, v.size) }.toSeq.sorted
  for ((grade, count) <- counts) {
    val stars = "*" * count
    println(s"$grade: $stars")
  }
}

val grades = Vector("A", "B", "A", "B", "B")

printHistogram(grades)

val fullGradeReporter = makeGradeReporter(fullGradeConverter, printHistogram)

val sampleGrades = Vector(5.0, 4.0, 4.4, 2.2, 3.3, 3.5)
fullGradeReporter(sampleGrades)

def plusMinusGradeConverter(grade: Double): String =
  if(grade <= 5.0 && grade > 4.7) "A"
  else if(grade <= 4.7 && grade > 4.3) "A-"
  else if(grade <= 4.3 && grade > 4.0) "B+"
  else if(grade <= 4.0 && grade > 3.7) "B"
  else if(grade <= 3.7 && grade > 3.3) "B-"
  else if(grade <= 3.3 && grade > 3.0) "C+"
  else if(grade <= 3.0 && grade > 2.7) "C"
  else if(grade <= 2.7 && grade > 2.3) "C-"
  else if(grade <= 2.3 && grade > 0.0) "D"
  else if(grade == 0.0) "F"
  else "N/A"

def printAllGrades(grades: Seq[String]): Unit =
  for(grade <- grades) println("Grade is: " + grade)

val plusMinusGradeReporter =
  makeGradeReporter(plusMinusGradeConverter, printAllGrades)

plusMinusGradeReporter(sampleGrades)