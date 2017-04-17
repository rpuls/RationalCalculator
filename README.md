# Operator Overloading

In Kotlin there is a fixed set of operators that can be used on types and classes in Kotlin. These operators have both a symbolic representation and a fixed precedence. 

## What is Operator Overloading?
Operator Overloading is the praxis of implementing functions on the fixed set of operators. 

## How to implement Operator Overloading?

To overload an Operator we need to write a function in Kotlin and annotate it with the 'operator' modifier. This way we map the function with the reserved name to the symbol 
One example is the binary operator 'a / b'

If we want to overload this, we need write a function which we map. The function we need to overload is:

'a.div(b)' 

To overload the operator we now need to implement our function as this:

```kotlin
operator fun div(other: Rational) :Rational {
        val div = gcd(this.n * other.d,this.d * other.n)
        return Rational((this.n * other.d)/div, (this.d * other.n)/div)
    }
```
In this function from the Rational Calculator we implement a division method to be able to divide rational numbers.

## Operator Overloading in Classes
Operator overloading can be used to make our code more readable, by implementing the operator inside the class

```kotlin
class Complex(val i: Int, val j: Int) {
    operator fun plus(c: Complex) = Complex(this.i + c.i, this.j + c.j)
}
```
This enables us to use the plus operator on Complex the following way:
```
val c = Complex(1, 0) + Complex(0, 1) // = Complex(1, 1)
``` 

## Operator Overloading in Extention functions

