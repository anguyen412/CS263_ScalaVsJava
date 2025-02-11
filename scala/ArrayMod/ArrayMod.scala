object ArrayMod {
    def doubleEachElement(list: List[Int]): List[Int] = {
        list.map(x => x * 2)
    }
    
    def main(args: Array[String]) = {
        println(doubleEachElement(List(1,2,3)))
    }
}