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
  def testPalindromeRecursive(blackhole: Blackhole, state: MyBenchmark.PalindromeRState): Boolean = {
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
        var array: Array[Int] = Array()
        
        @Setup(Level.Iteration)
        def doSetup(): Unit = {
            array = Array.range(1,1000000)
        }
        
        @TearDown(Level.Iteration)
        def doTearDown(): Unit = {
            System.gc()
        }
    }
    
    @State(Scope.Thread)
    class PalindromeState {
        var palin = ""
        
        @Setup(Level.Iteration)
        def doSetup(): Unit = {
            val range = Range(1,100000)
            for (r <- range) {
                palin += "a"
            }
        }
        
        @TearDown(Level.Iteration)
        def doTearDown(): Unit = {
            System.gc()
        }
    }
    
    @State(Scope.Thread)
    class PalindromeRState {
        var palin = ""
        
        @Setup(Level.Iteration)
        def doSetup(): Unit = {
            val range = Range(1,15000)
            for (r <- range) {
                palin += "a"
            }
        }
        
        @TearDown(Level.Iteration)
        def doTearDown(): Unit = {
            System.gc()
        }
    }
    
    @State(Scope.Thread)
    class LoopState {
        var sum: Long = 0
    }
    
    @State(Scope.Thread)
    class ListModState {
        var list: List[Int] = List()
        
        @Setup(Level.Iteration)
        def doSetup(): Unit = {
            list = List.range(1,1000000)
        }
        
        @TearDown(Level.Iteration)
        def toTearDown(): Unit = {
            System.gc()
        }
    }

    @State(Scope.Thread)
    class BinaryState {
      val a = "0100010101100101100100111100101001001010010010110111011011000010111100001010000000101000001011101111110101110011011110011111110110101011001010111101101110000111011100110101011001111110011000101100100111100111010011010000110011101000011001000000110000010111100101001100001110110011000101101111011110000110110001000011110011101010010101100110010010100110101111011010110000001111101010010000110001011011011111100010000111010100011010101001001000110101111100010000100010010101001001111101011101010100100001100101010001000011100000011100010100010001110011110000110011001101101101001101011100001001010000110011001100100011001100011000000000111111100110100001010110000010011110101111100000010010000111111100011001111001111011100011000110110101011110001100000100110101100110101100010010110101001001110111010100001000011111000001011101011100000101100001001101011110110110000001110001101010110110011100000110111111011001000101111001101110010111000111000001010010001101111000111011011011011110011111000110000010"
      val b = "1011011110011110001101100010110111000010010101110011100110100001010001101110111100001001001010010111111001001110000111000111010100110110000110011001110000110101001000001000101000010011110000001100010000011001101110110101101110101100100100010110010001001011001001000000000010111100100100001101010101000010101000100011110101100110100011101111110001101000000110110101101000011110010000011111011110100001000100100111100100110111111010110001011101111111100100100001000000011100010011000111101101101010110010101010010011000110000001001110100000111110100001010111110011001011010111001011011010010001000000001010101000111001001110111010010101011101000111111011001000110011011010101100011110110111010011011101010111000110111001110010011101011010100110000011001000011001101001000111010100000111011000000001111110011001101111011000111010100011111000010011110100010110101000001100110000100111110010111010001110110000111100010110011111110101111011110101111111000100011100101011001000100110110111000010100010000100"
    }
}