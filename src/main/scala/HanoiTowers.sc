def solve(disk_number: Int, source: String, aux: String, dest: String): Unit = {
  if (disk_number == 1) {
    println(s"$source -> $dest")
  } else {
    solve(disk_number -1, source, dest, aux)
    println(s"$source -> $dest")
    solve(disk_number -1, aux, source, dest)
  }
}

solve(3, "source", "aux", "dest")
println("   ")
solve(5, "source", "aux", "dest")


def solve2(disk_number: Int, source: String, aux: String, dest: String): Unit = {
  if (disk_number == 1) {
    println(s"$source -> $dest")
  } else {
    solve2(disk_number -1, source, dest, aux)
    solve2(1, source, "", dest)
    solve2(disk_number -1, aux, source, dest)
  }
}

solve2(3, "source", "aux", "dest")