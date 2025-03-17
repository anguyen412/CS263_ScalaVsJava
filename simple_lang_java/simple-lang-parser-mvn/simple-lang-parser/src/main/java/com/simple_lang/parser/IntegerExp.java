package com.simple_lang.parser;

public class IntegerExp implements Exp {
    public final String value;
    
    public IntegerExp(final String value) {
        this.value = value;
    }
    
    public String toString() {
        return "IntegerExp(" + value + ")";
    }
    
    public Boolean equlas(final Object other) {
        return (other instanceof IntegerExp && value == ((IntegerExp)other).value);
    }
}