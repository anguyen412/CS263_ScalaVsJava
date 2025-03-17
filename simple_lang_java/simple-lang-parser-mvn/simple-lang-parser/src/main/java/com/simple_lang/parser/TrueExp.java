package com.simple_lang.parser;

public class TrueExp implements Exp {
    
    public String toString() {
        return "TrueExp";
    }
    
    public boolean equals(final Object other) {
        return other instanceof TrueExp;
    }
}