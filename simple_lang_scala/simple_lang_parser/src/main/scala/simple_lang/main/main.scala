package simple_lang.main

import simple_lang.tokenizer._
import simple_lang.parser._

object Main {
    def main(args: Array[String]): Unit = {
        val my_string: String = "print(2+2);" 
        val tokens = Tokenizer(my_string)
        val ast = Parser(tokens)
        println(ast)
    }  
}

