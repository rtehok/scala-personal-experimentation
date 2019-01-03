import scala.io.Source
import scala.collection.JavaConverters._

case class CommandResult(status: Int, output: String, error: String)

val lsProcessBuilder = new ProcessBuilder("ls", "-la")
val lsProcess = lsProcessBuilder.start
Source.fromInputStream(lsProcess.getInputStream).mkString("")

class Command(commandParts: List[String]) {
  def run() = {
    val processBuilder = new ProcessBuilder(commandParts.asJava)
    val process = processBuilder.start()
    val status = process.waitFor()
    val outputAsString =
      Source.fromInputStream(process.getInputStream).mkString("")
    val errorAsString =
      Source.fromInputStream(process.getErrorStream).mkString("")
    CommandResult(status, outputAsString, errorAsString)
  }
}

object Command {
  def apply(commandString: String) = new Command(commandString.split("\\s").toList)
  def apply(commandParts: String*) = new Command(commandParts.toList)
}

Command("ls -la").run()

implicit class CommandString(firstCommandString: String) {
  def run() = Command(firstCommandString).run()

  def pipe(secondCommandString: String) = Vector(firstCommandString, secondCommandString)
}

"ls -la".run()

"ls -la" pipe "grep build"
// res3: scala.collection.immutable.Vector[String] = Vector(ls -la, grep build)

implicit class CommandVector(existingCommands: Vector[String]) {
  def run = {
    val pipedCommands = existingCommands.mkString(" | ")
    Command("/bin/sh", "-c", pipedCommands).run()
  }

  def pipe(nextCommand: String): Vector[String] = {
    existingCommands :+ nextCommand
  }
}

"ls -la ~/workspace/scala-personal-experimentation" pipe "grep pom" run
// res4: CommandResult = CommandResult(0,-rw-r--r--   1 tehokr  staff   740 Sep  2 16:07 pom.xml
// ,)

"ls -la ~/workspace/scala-personal-experimentation" pipe "grep pom" pipe "wc" run
// res5: CommandResult = CommandResult(0,       1       9      56
// ,)