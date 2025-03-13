package simple_lang.tokenizer;

import java.util.*;
import java.util.regex.*;

public class Tokenizer {

    // Reserved words
    private static Token reservedWords(String code) {
        if (code.startsWith("if")) return new IfToken();
        if (code.startsWith("else")) return new ElseToken();
        if (code.startsWith("while")) return new WhileToken();
        if (code.startsWith("print")) return new PrintToken();
        return null;
    }

    // Reserved symbols
    private static Token reservedSymbols(String code) {
        if (code.startsWith("(")) return new LeftParenToken();
        if (code.startsWith(")")) return new RightParenToken();
        if (code.startsWith("{")) return new LeftCurlyBraceToken();
        if (code.startsWith("}")) return new RightCurlyBraceToken();
        if (code.startsWith("=")) return new AssignmentToken();
        if (code.startsWith(";")) return new SemicolonToken();
        return null;
    }

    // Types
    private static Token types(String code) {
        if (code.startsWith("int")) return new IntToken();
        if (code.startsWith("bool")) return new BoolToken();
        if (code.startsWith("string")) return new StringToken();
        return null;
    }

    // Binary operators
    private static Token binaryOps(String code) {
        if (code.startsWith("+")) return new PlusToken();
        if (code.startsWith("-")) return new MinusToken();
        if (code.startsWith("<")) return new LessThanToken();
        if (code.startsWith(">")) return new GreaterThanToken();
        if (code.startsWith("==")) return new ExactlyEqualsToken();
        if (code.startsWith("!=")) return new NotEqualsToken();
        return null;
    }

    // Unary operators
    private static Token unaryOps(String code) {
        if (code.startsWith("!")) return new NotUnaryToken();
        if (code.startsWith("--")) return new MinusUnaryToken();
        return null;
    }

     // Expressions
    private static Token expressions(String code) {
        if (code.startsWith("true")) return new TrueBooleanLiteralToken();
        if (code.startsWith("false")) return new FalseBooleanLiteralToken();

        Matcher integerMatcher = Pattern.compile("[0-9]+").matcher(code);
        if (integerMatcher.find()) return new IntegerLiteralToken(integerMatcher.group());

        Matcher stringMatcher = Pattern.compile("\"[^\"]*\"").matcher(code);
        if (stringMatcher.find()) return new StringLiteralToken(stringMatcher.group());

        Matcher variableMatcher = Pattern.compile("[a-zA-Z_][a-zA-Z0-9_]*").matcher(code);
        if (variableMatcher.find()) return new VariableToken(variableMatcher.group());

        return null;
    }

    // Implementation of tokens in Tokenizer.scala
    public static List<Token> tokens(String code) {
        List<Token> tokens = new ArrayList<>();
        int index = 0;

        // Iterates through the string to generate a list of tokens
        while (index < code.length()) {

            // Match each substring to a token type
            String substring = code.substring(index);

            Token token = reservedWords(substring);
            if (token != null) {
                tokens.add(token);
                index += token.getLength();
                continue;
            }

            token = reservedSymbols(substring);
            if (token != null) {
                tokens.add(token);
                index += token.getLength();
                continue;
            }

            token = types(substring);
            if (token != null) {
                tokens.add(token);
                index += token.getLength();
                continue;
            }

            token = binaryOps(substring);
            if (token != null) {
                tokens.add(token);
                index += token.getLength();
                continue;
            }

            token = unaryOps(substring);
            if (token != null) {
                tokens.add(token);
                index += token.getLength();
                continue;
            }

            token = expressions(substring);
            if (token != null) {
                tokens.add(token);
                index += token.getLength();
                continue;
            }

            // Token not found (does something else need to be done?)
            //Yes, we can either throw an exception here... or return a null list
            //Since it's already set up to check for a null list in apply, I'll do that
            //index++; removing
            tokens = null;
            return tokens;
        }
        return tokens;
    }

    // Implementation of apply method in Tokenizer.scala
    public static List<Token> apply(String code) throws TokenizerException {
        List<Token> tokens = tokens(code);
        if (tokens == null) {
            throw new TokenizerException("Failure");
        }
        return tokens;
    }
    
}
