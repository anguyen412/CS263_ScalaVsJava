package simple_lang.tokenizer;

public class PrintToken implements Token {
    public int getLength() { return 5; }
    
    public String toString() {
        return "print";
    }
}