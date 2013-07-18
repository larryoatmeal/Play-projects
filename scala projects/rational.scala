class Rational(n: Int, d: Int){
	require(d != 0)

	def this(n: Int) = this(n, 1)

	private val g = gcd(n.abs, d.abs)
	private def gcd(a: Int, b: Int): Int = {
		if (b == 0) a else gcd(b, a%b)
	}

	val num = n/g
	val dem = d/g
	//println(this)



	def +(other: Rational) = {
		new Rational(num * other.dem + dem*other.num, dem * other.dem)
	}
	def +(other: Int) = {
		new Rational(num + dem*other, dem)
	}
	def +(other: Float): Rational ={
		//Convert Float to Rational. 5 decimal place accuracy
		val sign = if (other > 0) 1 else -1

		val integerPart: Int = other.abs.toInt
		//println("Integer" + integerPart)
		val decimalPart = other - integerPart //still a float
		//println("Decimal" + integerPart)
		val decimalShift5 = (decimalPart * 100000).toInt //now an integer
		//println("Fraction" + decimalShift5)

		val result: Rational= new Rational(decimalShift5, 100000) + integerPart
		
		this + result


	}

	def -(other: Rational) = {
		new Rational(num * other.dem - dem*other.num, dem * other.dem)
	}

	def *(other: Rational) = {
		new Rational(num * other.num, dem * other.dem)
	}

	def /(other: Rational) = {
		new Rational(num * other.dem, dem * other.num)
	}


	def &(other: Rational) = {
		new Rational(num + other.num, dem + other.dem)
	}


	override def toString = num + "/" + dem
}

object Rational{
	def main(args: Array[String]){
		val x = new Rational(1, 5)
		val y = new Rational(1, 4)
		val z = x + y
		val a = x & y
		val b = new Rational(9)
		val c = new Rational(6, 12)
		val d = x * y
		val e = x / y
		val f = x - y
		val g = x + 1
		val h = x + 0.688f
		val i = y + 89.32894f

		println(i)
		//val z = new Rational(4, 0)


		


		
	}

}
