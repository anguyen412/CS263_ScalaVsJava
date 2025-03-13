package simple_lang.tokenizer;

public class IntToken implements Token {
    public int getLength() { return 3; }
    
    public String toString() {
        return "int";
    }
}