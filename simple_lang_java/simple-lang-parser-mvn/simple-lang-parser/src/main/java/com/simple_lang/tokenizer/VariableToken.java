package simple_lang.tokenizer;

public class VariableToken implements Token {
    public final String name;
    
    public VariableToken(final String name) {
        this.name = name;
    }
    
    public int getLength() {
        return name.length();
    }
    
    public String toString() {
        return name;
    }
}