object Loop {
  def main(args: Array[String]): Unit = {
    val startTime = System.nanoTime()
    var sum: Long = 0
    for (i <- 0L until 1_000_000_000L) {
      sum += i
    }
    val endTime = System.nanoTime()
    println(s"Sum: $sum")
    println(s"Execution Time: ${endTime - startTime}")
  }
}
