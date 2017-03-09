package dk.cphbusiness.template

/**
 * Created by rasmus on 21-Feb-17.
 */
data class Rational(val n: Int, val d: Int) {

    override fun toString(): String{
        return "$n/$d"
    }

    operator fun times(other: Rational) =
            Rational(this.n * other.n, this.d * other.d)

    operator fun div(other: Rational) =
            Rational(this.n * other.d, this.d * other.n)

    operator fun plus(other: Rational) :Rational {
        if(this.d==other.d){
            return Rational(this.n+other.n,this.d)
        }else{
            return Rational(
                    this.n * other.d + other.n * this.d,
                    this.d * other.d
            )
        }
    }

    operator fun minus(other: Rational):Rational{
        if(this.d==other.d){
            return Rational(this.n-other.n,this.d)
        }else{
            return Rational(
                    this.n * other.d - other.n * this.d,
                    this.d * other.d
            )
        }
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

