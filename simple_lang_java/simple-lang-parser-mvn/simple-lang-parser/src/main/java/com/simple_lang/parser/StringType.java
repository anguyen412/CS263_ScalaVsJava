package com.simple_lang.parser;

public class StringType implements Type {
    
    public String toString() {
        return "StringType";
    }
    
    public boolean equals(final Object other) {
        return other instanceof StringType;
    }
}