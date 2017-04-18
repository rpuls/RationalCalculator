** Most of the examples in this text is taken from the RationCalculator that is found in this repository.**

The published version of this calculator made by [Rasmus Puls](https://github.com/rpuls) can be found on Google Play

[Rational Calculator](https://play.google.com/store/apps/details?id=dk.cphbusiness.template)

# Operator Overloading

In Kotlin there is a fixed set of operators that can be used on types and classes in Kotlin. These operators have both a symbolic representation and a fixed precedence. 

## What is Operator Overloading?
Operator Overloading is the praxis of implementing functions on the fixed set of operators. 

## How to implement Operator Overloading?

To overload an Operator we need to write a function in Kotlin and annotate it with the 'operator' modifier. This way we map the function with the reserved name to the symbol 
One example is the binary operator `a / b`

If we want to overload this, we need write a function which we map. The function we need to overload is:

`a.div(b)` 

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
```kotlin
val c = Complex(1, 0) + Complex(0, 1) // = Complex(1, 1)
``` 

Another simplified example would be to access a member list inside a member dataclass overloading the operator get().

```kotlin
data class MyMembers(val memberList: List<Member>) {
 operator fun get(pos: Int):  = memberList[pos]
}
```
Now we are able to access the memberlist using `MyMembers[4]` instead of `MyMembers.memberList[4]`

## Operator Overloading in Extention functions
There is also a possibility of using Operator Overloading with class extensions. Lets take the Rational Calculator Example again:

```kotlin
operator fun Int.times(r: Rational) = Rational(this,1) * r
```
In this example we use the built-in class and overload the times operator for that excact type using an extension function and we can now...

## Operator tables
As stated previously overloading can only be done on the prefixed operators.

### Unary operations

| Expression | Translated to |
| --- | --- |
| +a |	a.unaryPlus() |
|-a |	a.unaryMinus() |
!a|	a.not()
a++|	a.inc()
a–|	a.dec()

### Binary operations

| Expression | Translated to |
| --- | --- |
a + b |	a.plus(b)
a – b|	a.minus(b)
a * b|	a.times(b)
a / b|	a.div(b)
a % b|	a.mod(b)
a..b|	a.rangeTo(b)
a in b |	b.contains(a)
a !in b	|!b.contains(a)
a += b|	a.plusAssign(b)
a -= b|	a.minusAssign(b)
a *= b|	a.timesAssign(b)
a /= b|	a.divAssign(b)
a %= b|	a.modAssign(b)

### Array-like operations

| Expression | Translated to |
| --- | --- |
a[i]|	a.get(i)
a[i, j]|	a.get(i, j)
a[i_1, …, i_n]|	a.get(i_1, …, i_n)
a[i] = b|	a.set(i, b)
a[i, j] = b|	a.set(i, j, b)
a[i_1, …, i_n] = b|	a.set(i_1, …, i_n, b)

### Equals operation

| Expression | Translated to |
| --- | --- |
a == b|	a?.equals(b) ?: b === null
a != b|	!(a?.equals(b) ?: b === null)

### Function invocation

| Expression | Translated to |
| --- | --- |
a(i)|	a.invoke(i)
a(i, j)|	a.invoke(i, j)
a(i_1, …, i_n)|	a.invoke(i_1, …, i_n)

## How does the compiler handle operator overloading?

When doing operator Overloading, the we, as before mentioned, prefix the function with the `operator` keyword. 

when the compiler analyses yor code and execute the `operator` keyword and then goes trhough some steps to determine which overloaded function to use.

Example - Some operators that can be overloaded in kotlin

| Expression | Translated to |
| --- | --- |
a + b |	a.plus(b)
a – b|	a.minus(b)
a * b|	a.times(b)
a / b|	a.div(b)
a % b|	a.mod(b)
a..b|	a.rangeTo(b)

When the compiler processes an expression like a+b, it performs the following steps:

1. Determines the type of b.
2. Looks up a function a.plus(b) with the operator keyword and one parameter.
3. If the function is absent or ambiguous, it’s a compilation error.
4. If the function is present and it returns type R, the expression a+b has type R.

Example - Operator Overloading in kotlin

```kotlin
operator fun plus(other: Rational) :Rational {
   if(this.d==other.d){
       return Rational((this.n+other.n),this.d)
   }else{
       return Rational(
               (this.n * other.d + other.n * this.d),
               (this.d * other.d)
       )
   }
}
```

## Compared to Java

First of all Java doesn’t support user defined operator overloading, they choose to exclude it from the language as they felt it was abused in c++. In java It’s the compiler that magically overloads the + operator for String operands. The meaning of the + operator for Strings are defined by the language. Ints, floats, doubles etc. all have different binary representations and therefore adding two ints is a different operation in terms of bit manipulation. The addition depends on the nature of the objects being added but it’s defined on compile time and is not accessible for developers. For String operands the compiler generates code to concat strings, for int operands it generates code to add numbers.

Example - Operator overloading in java

```java
public static void main(String[] args) {
	String a = "Hello";
	String b = " world";
		
	int i = 1;
	int j = 2;
		
	//When adding Strings java uses string concatenation.
		
	System.out.println(a+b); //Evaluates to “Hello world”
		
	//When adding int's java uses math.
		
System.out.println(i+j); //Evaluates to 3
}
```

Operator overloading might not be necessary in java as you can just simulate it using method overloading.

Example - Overloading in java

```java
public static void main(String[] args) {
		
		int a = 1;
		int b = 2;
		
		Rational r1 = new Rational(1, 4);
		Rational r2 = new Rational(1, 4);
		
		System.out.println(plus(a, b).toString());
		System.out.println(plus(r1, r2).toString());
	}

	static Rational plus(int a, int b){
		return new Rational(a+b, 1);
	}

	static Rational plus(Rational a, Rational b){
		if(a.getD()==b.getD()){
			return new Rational(a.getN()+b.getN(),a.getD());
		}else{
			return new Rational((a.getN()*b.getD())+(b.getN()*a.getD()),
a.getD()*b.getD());
		}
	}
```
