package com.simple_lang.parser;

public class NotEqualsBinOp implements BinOp {
    
    public String toString() {
        return "NotEqualsBinOp";
    }
    
    public boolean equals(final Object other) {
        return other instanceof NotEqualsBinOp;
    }
}