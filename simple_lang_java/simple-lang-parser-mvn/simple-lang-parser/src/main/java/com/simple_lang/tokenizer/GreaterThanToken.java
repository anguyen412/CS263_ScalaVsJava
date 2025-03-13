package simple_lang.tokenizer;

public class GreaterThanToken implements Token {
    public int getLength() { return 1; }
    
    public String toString() {
        return ">";
    }
}