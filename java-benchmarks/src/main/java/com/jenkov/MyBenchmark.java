package com.jenkov;

import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

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
    
    @State(Scope.Thread)
    public static class ListModState {
        List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3));
    }
    
    @Benchmark @BenchmarkMode(Mode.SingleShotTime) @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public List<Integer> testListMod(Blackhole blackhole, ListModState state) {
        for (int i = 0; i < state.list.size(); i++) {
            state.list.set(i, state.list.get(i)*2);
        }
        blackhole.consume(state.list);
        return state.list;
    }
    
    //begin Alex written tests
    
    @State(Scope.Thread)
    public static class LoopState {
        long sum = 0;
    }
    
    @Benchmark @BenchmarkMode(Mode.SingleShotTime) @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public long testLoop(Blackhole blackhole, LoopState state) {
        for (long i = 0; i < 1_000_000_000L; i++) {
            state.sum += i;
        }
        blackhole.consume(state.sum);
        return state.sum;
    }
    
}