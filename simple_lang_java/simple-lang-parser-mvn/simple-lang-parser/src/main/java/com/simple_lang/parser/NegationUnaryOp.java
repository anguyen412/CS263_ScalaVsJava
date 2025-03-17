package com.simple_lang.parser;

public class NegationUnaryOp implements UnaryOp {
    
    public String toString() {
        return "NegationUnaryOp";
    }
    
    public boolean equals(final Object other) {
        return other instanceof NegationUnaryOp;
    }
}