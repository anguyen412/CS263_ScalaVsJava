class AddBinary {
    static String addBinary(String a, String b) {
        StringBuilder output = new StringBuilder();
        int carry = 0;
        int a_index = a.length()-1, b_index = b.length()-1;

        while (a_index >= 0 || b_index >= 0 || carry > 0) {
            int digit = carry;
            if (a_index >= 0 && a.charAt(a_index) == '1') {
                digit += 1;
            }
            if (b_index >= 0 && b.charAt(b_index) == '1') {
                digit += 1;
            }
            output.append(digit % 2);
            carry = digit / 2;
            a_index--;
            b_index--;
        }
        return output.reverse().toString();
    }

    public static void main(String[] args) {
        String a = "11";
        String b = "1";
        System.out.println(addBinary(a, b));
    }
}