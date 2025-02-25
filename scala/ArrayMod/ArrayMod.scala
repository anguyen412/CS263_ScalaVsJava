object ArrayMod {
    def doubleEachElement(array: Array[Int]): Array[Int] = {
        array.map(_ * 2)
    }
    
    def main(args: Array[String]) = {
        val result = doubleEachElement(Array(1,2,3))
        for (e <- result) println(e)
    }
}