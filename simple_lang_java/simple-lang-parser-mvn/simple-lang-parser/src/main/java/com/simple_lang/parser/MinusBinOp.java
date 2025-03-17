package com.simple_lang.parser;

public class MinusBinOp implements BinOp {
    
    public String toString() {
        return "MinusBinOp";
    }
    
    public boolean equals(final Object other) {
        return other instanceof MinusBinOp;
    }
} 