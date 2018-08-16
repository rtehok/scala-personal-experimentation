def asPrometheusCompliantLabelValue(labelValue: String): String = {
  val specificCharactersReplacements: Map[String, String] = Map("\\" -> "\\\\", "\n" -> "\\n", "\"" -> "\\\"")

  var value = labelValue
  specificCharactersReplacements.foreach {
    case (k, v) => value = value.replaceAllLiterally(k, v)
  }
  value

}

asPrometheusCompliantLabelValue(
  """jksdlajfkds
    |dsafd""".stripMargin)


def func(s: String): String = s.replaceAll("""\\""","""\\\\""")
  .replaceAll("""\n""","""\\n""")
  .replaceAll("""\"""","""\\"""")

func("""jksdlajfkds\\dsad\\dsa\\dsadsa""")
func("""jksdlajfkds\ndsafd""")
func("""jksdlajfkds\"ndsafd""")

"""hello { \" world \" } \n""".replaceAll("""([{}]|\\["n])""", "'$1'")

def func2(s: String): String = s.replaceAll("""\\""","""\\\\""")

func2("""jksdlajfkds\\dsad\\dsa\\dsadsa""")
func2("""jksdlajfkds\ndsafd""")
func2("""jksdlajfkds\"ndsafd\\fdfd dsfdsf\nfdsfds""")