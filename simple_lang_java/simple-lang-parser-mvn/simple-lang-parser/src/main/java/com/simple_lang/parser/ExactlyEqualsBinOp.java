package com.simple_lang.parser;

public class ExactlyEqualsBinOp implements BinOp {
    
    public String toString() {
        return "ExactlyEqualsBinOp";
    }
    
    public boolean equals(final Object other) {
        return other instanceof ExactlyEqualsBinOp;
    }
}