object Solution {
    def hammingDistance(x: Int, y: Int): Int = {
        // Additional References : https://tech.liuchao.me/2016/11/count-bits-of-integer/
        val z : Int = x ^ y
        (for (i <- 0 to 31 ; if ((z & (1 << i)) != 0)) yield(1)).sum
    }

    def main(args : Array[String]) {
        println("461 - Hamming Distance")
        assert(hammingDistance(1, 4) == 2)
        assert(hammingDistance(432, 1562) == 7)        
        println()
    }
}
