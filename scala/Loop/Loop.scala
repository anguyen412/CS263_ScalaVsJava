object Loop {
  def main(args: Array[String]): Unit = {
    var sum: Long = 0
    for (i <- 0L until 1_000_000_000L) {
      sum += i
    }
    println(s"Sum: $sum")
  }
}
