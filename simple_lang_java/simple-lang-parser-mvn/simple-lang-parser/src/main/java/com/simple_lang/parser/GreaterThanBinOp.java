package com.simple_lang.parser;

public class GreaterThanBinOp implements BinOp {
    
    public String toString() {
        return "GreaterThanBinOp";
    }
    
    public boolean equals(final Object other) {
        return other instanceof GreaterThanBinOp;
    }
}