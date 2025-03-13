package simple_lang.tokenizer;

public class NotEqualsToken implements Token {
    public int getLength() { return 2; }
    
    public String toString() {
        return "!=";
    }
}