package simple_lang.tokenizer;

public class FalseBooleanLiteralToken implements Token {
    public int getLength() { return 5; }
    
    public String toString() {
        return "false";
    }
}