package example

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.OutputTimeUnit
import org.openjdk.jmh.annotations.BenchmarkMode
import org.openjdk.jmh.annotations.Mode
import org.openjdk.jmh.infra.Blackhole
import java.util.concurrent.TimeUnit

class MyBenchmark {

  @Benchmark @OutputTimeUnit(TimeUnit.MILLISECONDS) @BenchmarkMode(Array(Mode.SingleShotTime)) 
  def testMethod(blackHole: Blackhole): Double = {
    val list: List[Int] = List.range(1, Integer.MAX_VALUE/100)
    val sum: Double = list.sum
    blackHole.consume(sum)
    sum
  }
  
  @Benchmark @OutputTimeUnit(TimeUnit.MILLISECONDS) @BenchmarkMode(Array(Mode.SingleShotTime))
//  def testArrayMod(): List[Int] = {
//  }
}