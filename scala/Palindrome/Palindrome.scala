object Palindrome {
    def isPalindrome(str: String): Boolean = {
        if (str == str.reverse) { true } else { false }
    }
    
    def main(args: Array[String]) = {
        println(isPalindrome("hi"))
        print(isPalindrome("ababa"))
    }
}