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
  
  //begin Alex written tests
  @Benchmark @OutputTimeUnit(TimeUnit.MILLISECONDS) @BenchmarkMode(Array(Mode.SingleShotTime))
  def testLoop(blackhole: Blackhole, state: MyBenchmark.LoopState): Long = {
      for (i <- 0L until 1_000_000_000L) {
          state.sum += i
      }
      blackhole.consume(state.sum)
      state.sum
  }

  def addBinary(a: String, b: String): String = {
      val sb = new StringBuilder
      var i = a.length - 1
      var j = b.length - 1
      var carry = 0

      while (i >= 0 || j >= 0 || carry > 0) {
          var sum = carry
          if (i >= 0 && a(i) == '1') { 
              sum += 1
          }
          if (j >= 0 && b(j) == '1') { 
              sum += 1  
          }
          sb.append(sum % 2)
          carry = sum / 2
          i -= 1
          j -= 1
      }
      sb.reverse.toString()
  }

  @Benchmark @OutputTimeUnit(TimeUnit.MILLISECONDS) @BenchmarkMode(Array(Mode.SingleShotTime))
  def testAddBinary(blackhole: Blackhole, state: MyBenchmark.BinaryState): String = {
      val result = addBinary(state.a, state.b)
      blackhole.consume(result)
      result
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
    class BinaryState {
      val a = "1101"
      val b = "0011"
    }
}