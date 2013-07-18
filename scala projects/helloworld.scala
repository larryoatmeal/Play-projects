println("Hello World")

def p(a: Any){
	println(a)
}
def pl(l: List[Any]){
	for (thing <- l){
		println("&" + thing)
	}
}

val n = 7
var i = 0
while (i < 7){
	if(i % 2 == 0) {println(i)}
	i+=1
}

args.foreach(arg => println(arg))
args.foreach(n => println(n+"?"))

for (i <- args){println(i + "*")}

val stringy = new Array[String](n)

for ( i <- 0 until n){
	stringy(i) = "stringy" + i
	println(stringy(i))
}

val pokemon = Array("pikachu", "blastoise")

for (item <- pokemon){
	println(item)
}

val roster = List(1, 2, 3, 6, 4)

//method() = blah
//calls the update method
//If method ends in colon, go from right to left!

println(299 :: roster)
println(roster)
println(roster.count(item => item > 4))
println(roster.drop(2))
println(roster(3))
println(roster(0))
println(roster.forall(s=>s>0))
p(roster.forall(_>0))
p(roster.foreach(print))
p(roster.head + roster.init.toString + roster.isEmpty.toString 
 + roster.last + roster.length)
pl(List("yes", "maybe", "no"))
pl(roster.reverse)
pl(roster.sortWith(
	(a, b) => a < b

	))

val triplet = (1, "hello", 8f)
p(triplet)
p(triplet._2)

//sets default immutbale
var setting = Set("Florida", "Computer", "Nice")
setting += "hello"
p(setting)
p(setting)

val instructions = Map[Int, String]( 1 -> "Believe", 2 -> "Doubt", 3 -> "Hi", 8 -> "no")
p(instructions)
p(instructions(2))
import scala.io.Source
if (args.length > 0){
	val lines = Source.fromFile(args(0)).getLines.toList
	val longestLine = lines.reduceLeft(
		(a, b) => if (a.length > b.length) a else b
		)
	println(longestLine)
	for (line <- lines){
		println(line.length +"| " +line )
	}
}


class pokeball{
	private var color = "red"
	def changeColor = {
		if (color == "red"){
			color = "blue"
		}else{
			color == "red"
		}
	}

	def unary_! = {
		color + "NOT!!!"
	}

	def indeed = {
		color + "SURE."
	}
	def surprise() = {
		color = "green"
		color
	}
	def setColor(color2 : String) = {
		color = color2
	}

	def +(other: pokeball) = {
		val sum = new pokeball
		sum.surprise()
		sum
	}

}

object pokeball{
	import scala.collection.mutable.Map

	private val pokedex = Map[String, Int]()

	def record(entry: pokeball){

	}

}


val fromat = """|Forever
				|Alone
				|Hahahaha
"""

p(fromat.stripMargin)

val s = 'hey
println(s.name)

//No side effects thing method
//Side effects thing method()

val red = new pokeball()
p(!red)

p(red indeed)
p(red surprise())

val pikachu = new pokeball()
val blastoise = new pokeball()
val sum = pikachu + blastoise

println(sum.indeed)



val pokedex = List("Pikachu", "Voltorb", "Charmander", "Blues", "mewtwo")

val types = 
	for {
		pokemon <- pokedex
		if (pokemon!="mewtwo")
		poketype = pokemon match {
		case "Pikachu" => "electric"
		case "Voltorb" => "electric"
		case "Charmander" => "fire"
		case _ => "?"}
	}
	yield poketype
	
println(types)

val noteMap = (flat: Boolean) => Map[Int, String](
	0 -> "C",
	1 -> (if (flat) "Db" else "C#"),
	2 -> "D",
	3 -> (if (flat) "Eb" else "D#"),
	4 -> "E",
	5 -> "F",
	6 -> (if (flat) "Gb" else "F#"),
	7 -> "G",
	8 -> (if (flat) "Ab" else "G#"),
	9 -> "A",
	10 -> (if (flat) "Bb" else "A#"),
	11 -> "B"
)

def midiToNote(midi: Int, flat: Boolean) = {
	require (!(midi < 0 || midi > 127))

	val octave = midi / 12 - 1
	val letter = noteMap(flat)(midi % 12)

	letter + octave
}

val myNote = midiToNote(83, false)

println(myNote)

val notes = (0 to 127).toList

val noteNames = for { note <- notes} yield midiToNote(note, false)

for (name <- noteNames){
	print(name + " | ")
}

class noteDisplay(noteLetter: String, accidental: String){
	val Natural = 0
	val Sharp = 1
	val DoubleSharp = 2
	val Flat = -1
	val DoubleFlat = -2

	def this(noteLetter: String) = this(noteLetter, "")

	val MapNoteLetters = Map[String, Int](
		"A" -> 9,
		"B" -> 11,
		"C" -> 0,
		"D" -> 2,
		"E" -> 4,
		"F" -> 5,
		"G" -> 7,
		"A" -> 9
	) 
	val MapAccidental = Map[String, Int](
		"" -> Natural,
		"#" -> Sharp,
		"##" -> DoubleSharp,
		"b" -> Flat,
		"bb" -> DoubleFlat
	)
	val fullNoteString = noteLetter + accidental
	//Getters
	val letter = noteLetter
	val acc = accidental

	val midi = MapNoteLetters(noteLetter.toUpperCase) +
		MapAccidental(accidental)
}


class interval(quality: String, distance: Int){
	val letterDistance = distance

	val midiDistance = 
		if(distance == 4 || distance == 5 || 
			distance == 1 || distance == 8){
			//Distance in halfsteps of perfect interval
			val perfectDistance = distance match{
				case 4 => 5
				case 5 => 7
				case 1 => 0
				case 8 => 12
			}
			//Adjust interval with Perfect as base point
			val perfectOffset = quality match {
				case "aug" => 1
				case "dim" => -1
				case "P" => 0
			} 

			perfectDistance + perfectOffset
		}
		else{
			//Distance in half steps of major interval
			val majorDistance = distance match{
				case 2 => 2
				case 3 => 4
				case 6 => 9
				case 7 => 11
			}
			val majorOffset = quality match{
				case "M" => 0
				case "m" => -1
				case "aug" => 1
				case "dim" => -2
			}


			majorDistance + majorOffset

		}
}



val notey = new noteDisplay("A")
println(notey.midi)

val intervaly = new interval("m", 7)
println(intervaly.midiDistance)












