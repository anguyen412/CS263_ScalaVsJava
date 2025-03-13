package simple_lang.tokenizer;

public class BoolToken implements Token {
    public int getLength() { return 4; }
    
    public String toString() {
        return "bool";
    }
}