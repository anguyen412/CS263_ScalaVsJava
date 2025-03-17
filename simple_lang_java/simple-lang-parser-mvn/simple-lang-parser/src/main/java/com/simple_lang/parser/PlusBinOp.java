package com.simple_lang.parser;

public class PlusBinOp implements BinOp {
    
    public String toString() {
        return "PlusBinOp";
    }
    
    public boolean equals(final Object other) {
        return other instanceof PlusBinOp;
    }
    
}