package simple_lang.tokenizer;

public class StringToken implements Token {
    public int getLength() { return 6; }
    
    public String toString() {
        return "string";
    }
}