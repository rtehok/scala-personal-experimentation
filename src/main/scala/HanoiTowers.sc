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