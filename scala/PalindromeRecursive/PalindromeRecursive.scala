object PalindromeRecursive {
    def isPalindrome(str: String): Boolean = {
        if (str.length == 0 || str.length == 1) { 
            return true 
        }
        if (str(0) == str(str.length-1)) {
            isPalindrome(str.substring(1, str.length-1))
        } else { false }
    }
    
    def main(args: Array[String]) = {
        println(isPalindrome("hi"))
        print(isPalindrome("ababa"))
    }
}