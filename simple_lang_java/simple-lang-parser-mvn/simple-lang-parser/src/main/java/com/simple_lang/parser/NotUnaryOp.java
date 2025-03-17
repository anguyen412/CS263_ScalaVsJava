package com.simple_lang.parser;

public class NotUnaryOp implements UnaryOp {
    
    public String toString() {
        return "NotUnaryOp";
    }
    
    public boolean equals(final Object other) {
        return other instanceof NotUnaryOp;
    }
}