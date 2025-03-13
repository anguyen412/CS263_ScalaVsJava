package simple_lang.tokenizer;

public class SemicolonToken implements Token {
    public int getLength() { return 1; }
    
    public String toString() {
        return ";";
    }
}