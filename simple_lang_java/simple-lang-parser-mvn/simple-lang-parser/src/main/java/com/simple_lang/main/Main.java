package com.simple_lang.main;

import simple_lang.tokenizer.*;

import java.util.List;

public class Main {
    public static void main(String[] args) throws TokenizerException {
        String code = "print(2+2);";
        List<Token> tokens = Tokenizer.apply(code);
        
        for (Token token : tokens) {
            System.out.print(token.toString());
        }
    }
}
