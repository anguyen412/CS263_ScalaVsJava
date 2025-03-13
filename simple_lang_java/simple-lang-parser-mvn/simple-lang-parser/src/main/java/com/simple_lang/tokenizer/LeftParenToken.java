package simple_lang.tokenizer;

public class LeftParenToken implements Token {
    public int getLength() { return 1; }
    
    public String toString() {
        return "(";
    }
}