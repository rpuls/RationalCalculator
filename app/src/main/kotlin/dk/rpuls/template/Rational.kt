package dk.rpuls.template

/**
 * Created by rasmus on 21-Feb-17.
 */
data class Rational(val n: Int, val d: Int) {

    override fun toString(): String{
        return "$n/$d"
    }

    operator fun times(other: Rational) :Rational {
        val div = gcd(this.n * other.n,this.d * other.d)
        return Rational((this.n * other.n)/div, (this.d * other.d)/div)
    }

    operator fun div(other: Rational) :Rational {
        val div = gcd(this.n * other.d,this.d * other.n)
        return Rational((this.n * other.d)/div, (this.d * other.n)/div)
    }

    operator fun plus(other: Rational) :Rational {
        if(this.d==other.d){
            val div = gcd(this.n+other.n,this.d)
            return Rational((this.n+other.n)/div,this.d/div)
        }else{
            val div = gcd((this.n * other.d + other.n * this.d),(this.d * other.d))
            return Rational(
                    (this.n * other.d + other.n * this.d)/div,
                    (this.d * other.d)/div
            )
        }
    }

    operator fun minus(other: Rational):Rational{
        if(this.d==other.d){
            val div = gcd(this.n-other.n,this.d)
            return Rational((this.n-other.n)/div,this.d/div)
        }else{
            val div = gcd((this.n * other.d - other.n * this.d),(this.d * other.d))
            return Rational(
                    (this.n * other.d - other.n * this.d)/div,
                    (this.d * other.d)/div
            )
        }
    }

    private fun gcd(a: Int, b: Int): Int {
        var a = a
        var b = b
        while (b > 0) {
            val temp = b
            b = a % b // % is remainder
            a = temp
        }
        return a
    }

}

operator fun Int.times(r: Rational) = Rational(this,1) * r

operator fun Int.div(r: Rational) = Rational(this,1) / r

operator fun Int.plus(r: Rational) = Rational(this, 1) + r

operator fun Int.minus(r: Rational) = Rational(this, 1) - r

//fun main(args: Array<String>) {
//    val a = Rational(22, 7)
//    val b = Rational(9, 5)
//    val atimesb = a * b
//    val adivb = a/b
//    val aplusb = a+b
//    val aminusb = a-b
//    println(atimesb)
//    println(adivb)
//    println(aplusb)
//    println(aminusb)
//    println(7*a)
//}

