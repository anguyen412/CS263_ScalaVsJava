package com.simple_lang.parser;

public class LessThanBinOp implements BinOp {
    
    public String toString() {
        return "LessThanBinOp";
    }
    
    public boolean equals(final Object other) {
        return other instanceof LessThanBinOp;
    }
}