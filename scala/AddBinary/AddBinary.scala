object AddBinary {
    def addBinary(a: String, b: String): String = {
        val sb = new StringBuilder
        var i = a.length - 1
        var j = b.length - 1
        var carry = 0

        while (i >= 0 || j >= 0 || carry > 0) {
            var sum = carry
            if (i >= 0 && a(i) == '1') { 
                sum += 1; 
                }
            if (j >= 0 && b(j) == '1') { 
                sum += 1;  
            }
            sb.append(sum % 2)
            carry = sum / 2
            i -= 1;
            j -= 1;
        }

        sb.reverse.toString()
    }

    def main(args: Array[String]): Unit = {
        val a = "11"
        val b = "1"
        val result = addBinary(a, b)
        println(result)
    }

}