package com.simple_lang.parser;

public class IntType implements Type {
    
    public String toString() {
        return "IntType";
    }
    
    public boolean equals(final Object other) {
        return other instanceof IntType;
    }
} 