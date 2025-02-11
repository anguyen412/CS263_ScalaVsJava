final class Palindrome {
    
    static boolean isPalindrome(String str) {
        int length = str.length();
        boolean result = false;
        for (int i = 0; i <= length; i++) {
            if (length == 0 || length == 1) {
                result = true;
            } else if (str.charAt(i) != str.charAt(length-1)) {
                result = false;
            }
            length--;
        }
        return result;
    }
    
    public static void main(String[] args) {
        System.out.println(isPalindrome("hi"));
        System.out.println(isPalindrome("ababa"));
    }
}