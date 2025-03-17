package com.simple_lang.parser;

public class FalseExp implements Exp {
    
    public String toString() {
        return "FalseExp";
    }
    
    public boolean equals(final Object other) {
        return other instanceof FalseExp;
    }
}