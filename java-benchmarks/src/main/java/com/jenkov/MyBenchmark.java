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
        public int[] list = new int[1_000_000];
        
        @Setup(Level.Iteration)
        public void doSetup() {
            for (int i = 0; i < list.length; i++) {
                list[i] = i;
            }
        }
        
        @TearDown(Level.Iteration)
        public void doTeardown() {
            System.gc();
        }
    }

    @Benchmark @BenchmarkMode(Mode.SingleShotTime) @OutputTimeUnit(TimeUnit.MILLISECONDS) @Fork(value = 1)// @Warmup(iterations = 2, time = 5)
    public int[] testArrayMod(Blackhole blackhole, ArrayModState state) {
        for (int i = 0; i < state.list.length; i++) {
            state.list[i] = state.list[i] * 2;
        }
        blackhole.consume(state.list);
        return state.list;
    }
    
    @State(Scope.Thread)
    public static class PalindromeState {
        String str = "";
        
        @Setup(Level.Iteration)
        public void doSetup() {
            for (int i = 0; i < 100_000; i++) {
                str += "a";
            }
        }
        
        @TearDown(Level.Iteration)
        public void doTearDown() {
            System.gc();
        }
        
    }
    
    @State(Scope.Thread)
    public static class PalindromeRState {
        String str = "";
        
        @Setup(Level.Iteration)
        public void doSetup() {
            for (int i = 0; i < 5_000; i++) {
                str += "a";
            }
        }
        
        @TearDown(Level.Iteration)
        public void doTearDown() {
            System.gc();
        }
        
    }
    
    @Benchmark @BenchmarkMode(Mode.SingleShotTime) @OutputTimeUnit(TimeUnit.MILLISECONDS) @Fork(value = 1)// @Warmup(iterations = 2, time = 5)
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
    
    @Benchmark @BenchmarkMode(Mode.SingleShotTime) @OutputTimeUnit(TimeUnit.MILLISECONDS) @Fork(value = 1)// @Warmup(iterations = 2, time = 5)
    public Boolean testPalindromeRecurisve(Blackhole blackhole, PalindromeRState state) {
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
        List<Integer> list = new ArrayList<>();
        
        @Setup(Level.Iteration)
        public void doSetup() {
            for (int i = 0; i < 1_000_000; i++) {
                list.add(i);
            }
        }
        
        @TearDown(Level.Iteration)
        public void doTearDown() {
            System.gc();
        }
    }
    
    @Benchmark @BenchmarkMode(Mode.SingleShotTime) @OutputTimeUnit(TimeUnit.MILLISECONDS) @Fork(value = 1)// @Warmup(iterations = 2, time = 5)
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
    
    @Benchmark @BenchmarkMode(Mode.SingleShotTime) @OutputTimeUnit(TimeUnit.MILLISECONDS) @Fork(value = 1)// @Warmup(iterations = 2, time = 5)
    public long testLoop(Blackhole blackhole, LoopState state) {
        for (long i = 0; i < 1_000_000_000L; i++) {
            state.sum += i;
        }
        blackhole.consume(state.sum);
        return state.sum;
    }

    static String addBinary(String a, String b) {
        StringBuilder output = new StringBuilder();
        int carry = 0;
        int a_index = a.length()-1, b_index = b.length()-1;

        while (a_index >= 0 || b_index >= 0 || carry > 0) {
            int digit = carry;
            if (a_index >= 0 && a.charAt(a_index) == '1') {
                digit += 1;
            }
            if (b_index >= 0 && b.charAt(b_index) == '1') {
                digit += 1;
            }
            output.append(digit % 2);
            carry = digit / 2;
            a_index--;
            b_index--;
        }
        return output.reverse().toString();
    }
    
    @State(Scope.Thread)
    public static class BinaryState {
        String a = "0100010101100101100100111100101001001010010010110111011011000010111100001010000000101000001011101111110101110011011110011111110110101011001010111101101110000111011100110101011001111110011000101100100111100111010011010000110011101000011001000000110000010111100101001100001110110011000101101111011110000110110001000011110011101010010101100110010010100110101111011010110000001111101010010000110001011011011111100010000111010100011010101001001000110101111100010000100010010101001001111101011101010100100001100101010001000011100000011100010100010001110011110000110011001101101101001101011100001001010000110011001100100011001100011000000000111111100110100001010110000010011110101111100000010010000111111100011001111001111011100011000110110101011110001100000100110101100110101100010010110101001001110111010100001000011111000001011101011100000101100001001101011110110110000001110001101010110110011100000110111111011001000101111001101110010111000111000001010010001101111000111011011011011110011111000110000010";
        String b = "1011011110011110001101100010110111000010010101110011100110100001010001101110111100001001001010010111111001001110000111000111010100110110000110011001110000110101001000001000101000010011110000001100010000011001101110110101101110101100100100010110010001001011001001000000000010111100100100001101010101000010101000100011110101100110100011101111110001101000000110110101101000011110010000011111011110100001000100100111100100110111111010110001011101111111100100100001000000011100010011000111101101101010110010101010010011000110000001001110100000111110100001010111110011001011010111001011011010010001000000001010101000111001001110111010010101011101000111111011001000110011011010101100011110110111010011011101010111000110111001110010011101011010100110000011001000011001101001000111010100000111011000000001111110011001101111011000111010100011111000010011110100010110101000001100110000100111110010111010001110110000111100010110011111110101111011110101111111000100011100101011001000100110110111000010100010000100";
        
        @TearDown(Level.Iteration)
        public void doTearDown() {
            System.gc();
        }
    }
    
    @Benchmark @BenchmarkMode(Mode.SingleShotTime) @OutputTimeUnit(TimeUnit.MILLISECONDS) @Fork(value = 1)// @Warmup(iterations = 2, time = 5)
    public String testAddBinary(Blackhole blackhole, BinaryState state) {
        String result = addBinary(state.a, state.b);
        blackhole.consume(result);
        return result;
    }    
}