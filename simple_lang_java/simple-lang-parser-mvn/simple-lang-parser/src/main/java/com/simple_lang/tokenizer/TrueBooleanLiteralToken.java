package simple_lang.tokenizer;

public class TrueBooleanLiteralToken implements Token {
    public int getLength() { return 4; }
    
    public String toString() {
        return "true";
    }
}