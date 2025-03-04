package example

import org.openjdk.jmh.annotations._
import org.openjdk.jmh.infra.Blackhole
import java.util.concurrent.TimeUnit

class MyBenchmark {
  
  @Benchmark @OutputTimeUnit(TimeUnit.MILLISECONDS) @BenchmarkMode(Array(Mode.SingleShotTime))
  def testArrayMod(blackhole: Blackhole, state: MyBenchmark.ArrayModState): Array[Int] = {
      state.array.map(_ * 2)
      blackhole.consume(state.array)
      state.array
  }
  
  @Benchmark @OutputTimeUnit(TimeUnit.MILLISECONDS) @BenchmarkMode(Array(Mode.SingleShotTime))
  def testPalindrome(blackhole: Blackhole, state: MyBenchmark.PalindromeState): Boolean = {
      if (state.palin == state.palin.reverse) { true } else { false }
  }
  
  def isPalindrome(str: String): Boolean = {
      if (str.length == 0 || str.length == 1) {
          return true
      }
      if (str(0) == str(str.length-1)) {
          isPalindrome(str.substring(1, str.length-1))
      } else { false }
  }
  
  @Benchmark @OutputTimeUnit(TimeUnit.MILLISECONDS) @BenchmarkMode(Array(Mode.SingleShotTime))
  def testPalindromeRecursive(blackhole: Blackhole, state: MyBenchmark.PalindromeState): Boolean = {
      val retVal = isPalindrome(state.palin)
      blackhole.consume(retVal)
      retVal
  }
  
  @Benchmark @OutputTimeUnit(TimeUnit.MILLISECONDS) @BenchmarkMode(Array(Mode.SingleShotTime))
  def testListMod(blackhole: Blackhole, state: MyBenchmark.ListModState): List[Int] = {
      state.list.map(x => x * 2)
  }
  
  //begin Alex written tests
  @Benchmark @OutputTimeUnit(TimeUnit.MILLISECONDS) @BenchmarkMode(Array(Mode.SingleShotTime))
  def testLoop(blackhole: Blackhole, state: MyBenchmark.LoopState): Long = {
      for (i <- 0L until 1_000_000_000L) {
          state.sum += i
      }
      blackhole.consume(state.sum)
      state.sum
  }
}

object MyBenchmark {
    @State(Scope.Thread)
    class ArrayModState {
        val array = Array(1,2,3)
    }
    
    @State(Scope.Thread)
    class PalindromeState {
        val palin = "ababa"
    }
    
    @State(Scope.Thread)
    class LoopState {
        var sum: Long = 0
    }
    
    @State(Scope.Thread)
    class ListModState {
        val list = List(1,2,3)
    }
}