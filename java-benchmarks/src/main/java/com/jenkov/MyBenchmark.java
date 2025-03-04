package com.jenkov;

import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

public class MyBenchmark {
    
    @State(Scope.Thread)
    public static class ArrayModState {
        public int[] list = {1,2,3};
    }

    @Benchmark @BenchmarkMode(Mode.SingleShotTime) @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public int[] testArrayMod(Blackhole blackhole, ArrayModState state) {
        for (int i = 0; i < state.list.length; i++) {
            state.list[i] = state.list[i] * 2;
        }
        blackhole.consume(state.list);
        return state.list;
    }
    
    @State(Scope.Thread)
    public static class PalindromeState {
        String str = "hi";
    }
    
    @Benchmark @BenchmarkMode(Mode.SingleShotTime) @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public boolean testPalindrome(PalindromeState state, Blackhole blackhole) {
        int length = state.str.length();
        boolean result = false;
        for (int i = 0; i <= length; i++) {
            if (length == 0 || length == 1) {
                result = true;
            } else if (state.str.charAt(i) != state.str.charAt(length-1)) {
                result = false;
            }
            length--;
        }
        blackhole.consume(result);
        return result;
    }
    
    @Benchmark @BenchmarkMode(Mode.SingleShotTime) @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public Boolean testPalindromeRecurisve(Blackhole blackhole, PalindromeState state) {
        Boolean returnVal = isPalindrome(state.str);
        blackhole.consume(returnVal);
        return returnVal;
    }
    
    private Boolean isPalindrome(String str) {
        if (str.length() == 1 || str.length() == 0) {
            return true;
        }
        if (str.charAt(0) == str.charAt(str.length()-1)) {
            return isPalindrome(str.substring(1,str.length()-1));
        } else {
            return false;
        }
    }
    
    @Benchmark @BenchmarkMode(Mode.SingleShotTime) @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public long testLoop(Blackhole blackhole) {
        long sum = 0;
        for (long i = 0; i < 1_000_000_000L; i++) {
            sum += i;
        }
        blackhole.consume(sum);
        return sum;
    }
    
}