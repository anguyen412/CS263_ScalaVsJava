package com.simple_lang.parser;

public class BoolType implements Type {
    
    public String toString() {
        return "BoolType";
    }
    
    public boolean equals(final Object other) {
        return other instanceof BoolType;
    }
}