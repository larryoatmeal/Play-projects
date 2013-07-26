// // // println("Hello World")

// // // def p(a: Any){
// // // 	println(a)
// // // }
// // // def pl(l: List[Any]){
// // // 	for (thing <- l){
// // // 		println("&" + thing)
// // // 	}
// // // }

// // // val n = 7
// // // var i = 0
// // // while (i < 7){
// // // 	if(i % 2 == 0) {println(i)}
// // // 	i+=1
// // // }

// // // args.foreach(arg => println(arg))
// // // args.foreach(n => println(n+"?"))

// // // for (i <- args){println(i + "*")}

// // // val stringy = new Array[String](n)

// // // for ( i <- 0 until n){
// // // 	stringy(i) = "stringy" + i
// // // 	println(stringy(i))
// // // }

// // // val pokemon = Array("pikachu", "blastoise")

// // // for (item <- pokemon){
// // // 	println(item)
// // // }

// // // val roster = List(1, 2, 3, 6, 4)

// // // //method() = blah
// // // //calls the update method
// // // //If method ends in colon, go from right to left!

// // // println(299 :: roster)
// // // println(roster)
// // // println(roster.count(item => item > 4))
// // // println(roster.drop(2))
// // // println(roster(3))
// // // println(roster(0))
// // // println(roster.forall(s=>s>0))
// // // p(roster.forall(_>0))
// // // p(roster.foreach(print))
// // // p(roster.head + roster.init.toString + roster.isEmpty.toString 
// // //  + roster.last + roster.length)
// // // pl(List("yes", "maybe", "no"))
// // // pl(roster.reverse)
// // // pl(roster.sortWith(
// // // 	(a, b) => a < b

// // // 	))

// // // val triplet = (1, "hello", 8f)
// // // p(triplet)
// // // p(triplet._2)

// // // //sets default immutbale
// // // var setting = Set("Florida", "Computer", "Nice")
// // // setting += "hello"
// // // p(setting)
// // // p(setting)

// // // val instructions = Map[Int, String]( 1 -> "Believe", 2 -> "Doubt", 3 -> "Hi", 8 -> "no")
// // // p(instructions)
// // // p(instructions(2))
// // // import scala.io.Source
// // // if (args.length > 0){
// // // 	val lines = Source.fromFile(args(0)).getLines.toList
// // // 	val longestLine = lines.reduceLeft(
// // // 		(a, b) => if (a.length > b.length) a else b
// // // 		)
// // // 	println(longestLine)
// // // 	for (line <- lines){
// // // 		println(line.length +"| " +line )
// // // 	}
// // // }


// // // class pokeball{
// // // 	private var color = "red"
// // // 	def changeColor = {
// // // 		if (color == "red"){
// // // 			color = "blue"
// // // 		}else{
// // // 			color == "red"
// // // 		}
// // // 	}

// // // 	def unary_! = {
// // // 		color + "NOT!!!"
// // // 	}

// // // 	def indeed = {
// // // 		color + "SURE."
// // // 	}
// // // 	def surprise() = {
// // // 		color = "green"
// // // 		color
// // // 	}
// // // 	def setColor(color2 : String) = {
// // // 		color = color2
// // // 	}

// // // 	def +(other: pokeball) = {
// // // 		val sum = new pokeball
// // // 		sum.surprise()
// // // 		sum
// // // 	}

// // // }

// // // object pokeball{
// // // 	import scala.collection.mutable.Map

// // // 	private val pokedex = Map[String, Int]()

// // // 	def record(entry: pokeball){

// // // 	}

// // // }


// // // val fromat = """|Forever
// // // 				|Alone
// // // 				|Hahahaha
// // // """

// // // p(fromat.stripMargin)

// // // val s = 'hey
// // // println(s.name)

// // // //No side effects thing method
// // // //Side effects thing method()

// // // val red = new pokeball()
// // // p(!red)

// // // p(red indeed)
// // // p(red surprise())

// // // val pikachu = new pokeball()
// // // val blastoise = new pokeball()
// // // val sum = pikachu + blastoise

// // // println(sum.indeed)



// // // val pokedex = List("Pikachu", "Voltorb", "Charmander", "Blues", "mewtwo")

// // // val types = 
// // // 	for {
// // // 		pokemon <- pokedex
// // // 		if (pokemon!="mewtwo")
// // // 		poketype = pokemon match {
// // // 		case "Pikachu" => "electric"
// // // 		case "Voltorb" => "electric"
// // // 		case "Charmander" => "fire"
// // // 		case _ => "?"}
// // // 	}
// // // 	yield poketype
	
// // // println(types)

// // // val noteMap = (flat: Boolean) => Map[Int, String](
// // // 	0 -> "C",
// // // 	1 -> (if (flat) "Db" else "C#"),
// // // 	2 -> "D",
// // // 	3 -> (if (flat) "Eb" else "D#"),
// // // 	4 -> "E",
// // // 	5 -> "F",
// // // 	6 -> (if (flat) "Gb" else "F#"),
// // // 	7 -> "G",
// // // 	8 -> (if (flat) "Ab" else "G#"),
// // // 	9 -> "A",
// // // 	10 -> (if (flat) "Bb" else "A#"),
// // // 	11 -> "B"
// // // )

// // // def midiToNote(midi: Int, flat: Boolean) = {
// // // 	require (!(midi < 0 || midi > 127))

// // // 	val octave = midi / 12 - 1
// // // 	val letter = noteMap(flat)(midi % 12)

// // // 	letter + octave
// // // }

// // // val myNote = midiToNote(83, false)

// // // println(myNote)

// // // val notes = (0 to 127).toList

// // // val noteNames = for { note <- notes} yield midiToNote(note, false)

// // // for (name <- noteNames){
// // // 	print(name + " | ")
// // // }

// // // class noteDisplay(noteLetter: String, accidental: String){
// // // 	val Natural = 0
// // // 	val Sharp = 1
// // // 	val DoubleSharp = 2
// // // 	val Flat = -1
// // // 	val DoubleFlat = -2

// // // 	def this(noteLetter: String) = this(noteLetter, "")

// // // 	val MapNoteLetters = Map[String, Int](
// // // 		"A" -> 9,
// // // 		"B" -> 11,
// // // 		"C" -> 0,
// // // 		"D" -> 2,
// // // 		"E" -> 4,
// // // 		"F" -> 5,
// // // 		"G" -> 7,
// // // 		"A" -> 9
// // // 	) 
// // // 	val MapAccidental = Map[String, Int](
// // // 		"" -> Natural,
// // // 		"#" -> Sharp,
// // // 		"##" -> DoubleSharp,
// // // 		"b" -> Flat,
// // // 		"bb" -> DoubleFlat
// // // 	)
// // // 	val fullNoteString = noteLetter + accidental
// // // 	//Getters
// // // 	val letter = noteLetter
// // // 	val acc = accidental

// // // 	val midi = MapNoteLetters(noteLetter.toUpperCase) +
// // // 		MapAccidental(accidental)
// // // }


// // // class interval(quality: String, distance: Int){
// // // 	val letterDistance = distance

// // // 	val midiDistance = 
// // // 		if(distance == 4 || distance == 5 || 
// // // 			distance == 1 || distance == 8){
// // // 			//Distance in halfsteps of perfect interval
// // // 			val perfectDistance = distance match{
// // // 				case 4 => 5
// // // 				case 5 => 7
// // // 				case 1 => 0
// // // 				case 8 => 12
// // // 			}
// // // 			//Adjust interval with Perfect as base point
// // // 			val perfectOffset = quality match {
// // // 				case "aug" => 1
// // // 				case "dim" => -1
// // // 				case "P" => 0
// // // 			} 

// // // 			perfectDistance + perfectOffset
// // // 		}
// // // 		else{
// // // 			//Distance in half steps of major interval
// // // 			val majorDistance = distance match{
// // // 				case 2 => 2
// // // 				case 3 => 4
// // // 				case 6 => 9
// // // 				case 7 => 11
// // // 			}
// // // 			val majorOffset = quality match{
// // // 				case "M" => 0
// // // 				case "m" => -1
// // // 				case "aug" => 1
// // // 				case "dim" => -2
// // // 			}


// // // 			majorDistance + majorOffset

// // // 		}
// // // }



// // // val notey = new noteDisplay("A")
// // // println(notey.midi)

// // // val intervaly = new interval("m", 7)
// // // println(intervaly.midiDistance)



// // def nookPrinter(words: List[String], converter: (String) => String){
// // 	words.foreach(word => println(converter(word)))
// // }
// // def flowerPrinter(words: List[String]) = 
// // 	nookPrinter(words, (x) => x + "*" )
// // def flowerPrinter2(words: List[String]) = 
// // 	nookPrinter(words, _ + "**" )

// // def upperPrinter(words:List[String]) = 
// // 	nookPrinter(words, _.toUpperCase)





// // flowerPrinter2(List("hello", "what", "is"))
// // upperPrinter(List("Hi", "my", "name", "is"))

// // def twice(op: Double => Double, x: Double) = {
// // 	op(op(x))
// // }

// // def pow4(x: Double) = twice( a => a * a, x)
// // println(twice(x => x/2.0, 5))


// // println(pow4(2))


// // def thrice(x: Double)(op: Double => Double) = {
// // 	op(op(op(x)))
// // }

// // val d = thrice(3){
// // 	x => x + x % 2
// // }

// // def thrice6 = thrice(6)_


// // println(thrice6{
// // 	x => x/2
// // })



// // println(d)

// // val assertionEnabled = true
// // def byNameAssert(predicate: => Boolean){
// // 	if(assertionEnabled && !predicate){
// // 		println("ERROR!!!!!")		
// // 	}
// // }


// // byNameAssert{
// // 	5 < 3

// // }


// // def splitIntoBars(input: String, bars: List[String]): List[String] = {
// // 			//Find location of first |
// // 			//Find location of next |
// // 			//Extract bar, add it to the array, and pass in the remaining recusrively
// // 			//If can't find either, discard bar and return bar list


// // 			val firstBar = input.indexOf('|')
// // 			input.drop(firstBar)

// // 			val nextBar = input.indexOf('|',firstBar + 1) //find one after firsbar

// // 			if (firstBar != -1 && nextBar != -1){
// // 				//drop everything to left of firstBar, then split
// // 				// input.splitAt(nextBar) match {
// // 				// 	case Tuple2(bar, remainder) => 
// // 				// 		splitIntoBars(remainder, bar :: bars )
// // 				// 	case _ => bars
// // 				// }
// // 				val bar = input.substring(firstBar + 1, nextBar)//Don't want | in stored bar
// // 				val rest = input.substring(nextBar)
// // 				splitIntoBars(rest, bar :: bars)
// // 			}else{
// // 				bars //if no more bars, return final result
// // 			}
// // 		}

// // println(splitIntoBars("  898| | Cm7 Dmaj7 | G7 Gb7#5 | Fsus9 |", List()).reverse)


// // /*

// // | Cm7 D | 



// // */





// // abstract class Element {
// // 	def contents: Array[String]
// // 	def height: Int = contents.length
// // 	def width: Int = if (height == 0) 0 else contents(0).length
// // }

// // // class ArrayElement(conts: Array[String]) extends Element{
// // // 	def contents: Array[String] = conts
// // // 	//could do val contents: Array[String] = conts

// // // }

// // class ArrayElement(
// // 	val contents: Array[String]
// // 	) extends Element

// // // class LineElement(s: String) extends ArrayElement(Array(s)){
// // // 	override def width = s.length
// // // 	override def height = 1
// // // }

// // class LineElement(s: String) extends Element{
// // 	def contents= Array(s)
// // 	override def height= 1
// // 	override def width= s.length
// // 	} 

// // class UniformELement(
// // 	ch: Char,
// // 	override val width: Int,
// // 	override val height: Int
// // 	) extends Element {

// // 	private val line = ch.toString * width
// // 	def contents = Array(height).map(row => line)
// // }

// // //def above(that: Element) = new ArrayElement(this.contents ++ that.contents)













// // val ae = new ArrayElement(Array("Larry", "Wang"))
// // println(ae.height)














// // 		def splitIntoChords(input: String, chords: List[String]): List[String] ={
			
// // 			//Remove leading whitespace so chord will start at pos 0 of string
// // 			val inputNoLead = input.dropWhile(_ == ' ')


// // 			val firstSpace = inputNoLead.indexOf(' ')

// // 			if (inputNoLead.length > 0){
// // 				val chord = if (firstSpace != -1){
// // 					inputNoLead.substring(0, firstSpace)//doesn't include whitespace	
// // 				} else{
// // 					inputNoLead.substring(0) // If no space at end, just take the entire string
// // 				}

// // 				val rest = if (firstSpace != -1)
// // 				{inputNoLead.substring(firstSpace)}else{
// // 					"" //if no spaces left, chord will alread have been taken and no more chords left to take
// // 				}


// // 				splitIntoChords(rest, chord :: chords)
// // 			}else{
// // 				chords
// // 			}	
// // 		}


// // println(splitIntoChords("Cm7 Dm7", List()))

// case class Bar(chords: List[String])

// def parseText(raw: String) = {
// 		def splitIntoBars(input: String, bars: List[String]): List[String] = {
// 			//Find location of first |
// 			//Find location of next |
// 			//Extract bar, add it to the array, and pass in the remaining recusrively
// 			//If can't find either, discard bar and return bar list


// 			val firstBar = input.indexOf('|')
// 			input.drop(firstBar)

// 			val nextBar = input.indexOf('|',firstBar + 1) //find one after firsbar

// 			if (firstBar != -1 && nextBar != -1){
// 				//drop everything to left of firstBar, then split
// 				// input.splitAt(nextBar) match {
// 				// 	case Tuple2(bar, remainder) => 
// 				// 		splitIntoBars(remainder, bar :: bars )
// 				// 	case _ => bars
// 				// }
// 				val bar = input.substring(firstBar + 1, nextBar)//Don't want | in stored bar
// 				val rest = input.substring(nextBar)
// 				splitIntoBars(rest, bar :: bars)
// 			}else{
// 				bars //if no more bars, return final result
// 			}
// 		}

		
// 		//Edit bars so that there are no leading spaces
		
// 		// val barsSpaced = bars.map(
// 		// 	bar => evenWhiteSpace(bar.dropWhile(_ == ' '))//drop leading whitespace first
// 		// )

// 		def splitIntoChords(input: String, chords: List[String]): List[String] ={	
// 			//Remove leading whitespace so chord will start at pos 0 of string
// 			val inputNoLead = input.dropWhile(_ == ' ')


// 			val firstSpace = inputNoLead.indexOf(' ')

// 			if (inputNoLead.length > 0){
// 				val chord = if (firstSpace != -1){
// 					inputNoLead.substring(0, firstSpace)//doesn't include whitespace	
// 				} else{
// 					inputNoLead.substring(0) // If no space at end, just take the entire string
// 				}

// 				val rest = if (firstSpace != -1)
// 				{inputNoLead.substring(firstSpace)}else{
// 					"" //if no spaces left, chord will alread have been taken and no more chords left to take
// 				}

// 				splitIntoChords(rest, chord :: chords)
// 			}else{
// 				chords
// 			}	
// 		}

// 		val barStrings = splitIntoBars(raw, List[String]()).reverse

// 		val bars = barStrings.map(
// 			barString => Bar(splitIntoChords(barString, List()))
// 		)

// 		bars
// 	}


// 		def formatSong(songtext: String){

// 		val bars = parseText(songtext)
// 		val longestChord = bars.foldLeft(0)((b, bar) => {
// 			val longestChordInBar = bar.chords.foldLeft(0)((c, chord) =>


// 				if(chord.length > c){
// 					chord.length
// 				}else{
// 					c
// 				}

// 			)
// 			println(longestChordInBar + bar.toString)

// 			if (longestChordInBar > b){
// 				longestChordInBar
// 			}else{
// 				b
// 			}

// 		}

// 		)
// 				println(longestChord)

// 		val template = "|" + ("1"*longestChord + " ") +
// 			("2"*longestChord + " ") +
// 			("3"*longestChord + " ") +
// 			("4"*longestChord + " ") 


// 		var output = "" //!!!! MUTABLE STATE

// 		def padWhiteSpace(chord: String) = {
// 			chord + " "*(longestChord-chord.length)
// 		}

// 		bars.foreach(
// 			bar => {
// 				val numChords = bar.chords.length
// 				val barString = numChords match {
// 					case 1 => {
// 						template.replace("1"*longestChord,padWhiteSpace(bar.chords(0))).
// 						replace("2"*longestChord," ").
// 						replace("3"*longestChord," ").
// 						replace("4"*longestChord," ")
// 					}
// 					case 2 => {
// 						template.replace("1"*longestChord,padWhiteSpace(bar.chords(0))).
// 						replace("2"*longestChord," ").
// 						replace("3"*longestChord,padWhiteSpace(bar.chords(1))).
// 						replace("4"*longestChord," ")
// 					}
// 					case 3 => {
// 						template.replace("1"*longestChord,padWhiteSpace(bar.chords(0))).
// 						replace("2"*longestChord," ").
// 						replace("3"*longestChord,padWhiteSpace(bar.chords(1))).
// 						replace("4"*longestChord,padWhiteSpace(bar.chords(2)))
// 					}
// 					case 4 => {
// 						template.replace("1"*longestChord,padWhiteSpace(bar.chords(0))).
// 						replace("2"*longestChord,padWhiteSpace(bar.chords(1))).
// 						replace("3"*longestChord,padWhiteSpace(bar.chords(2))).
// 						replace("4"*longestChord,padWhiteSpace(bar.chords(3)))
// 					}
// 					case 0 => {
// 						template.replace("1"*longestChord," ").
// 						replace("2"*longestChord," ").
// 						replace("3"*longestChord," ").
// 						replace("4"*longestChord," ")
// 					}
// 					case _ => {
// 						template
// 					}
// 				}

// 				print(barString)
// 			}

// 		)

// 	}


// 	formatSong("|Cm7 Dm9| Gm7 G| Am7 Dm7||")



//case class Chord(root: String, quality: String)
case class Song(content: String, user_id: Int, composer: Option[String], date: Option[String])
//case class Song(bars: List[Bar], user_id: Int)
/*
	| * * | * | * * * | * * * * |
	|: barline
	*: chord
*/

case class Bar(chords: List[String])


object ChordM{
	val TimeSig = 4

	//helpers
	
	def parseText(raw: String) = {
		def splitIntoBars(input: String, bars: List[String]): List[String] = {
			//Find location of first |
			//Find location of next |
			//Extract bar, add it to the array, and pass in the remaining recusrively
			//If can't find either, discard bar and return bar list


			val firstBar = input.indexOf('|')
			input.drop(firstBar)

			val nextBar = input.indexOf('|',firstBar + 1) //find one after firsbar

			if (firstBar != -1 && nextBar != -1){
				//drop everything to left of firstBar, then split
				// input.splitAt(nextBar) match {
				// 	case Tuple2(bar, remainder) => 
				// 		splitIntoBars(remainder, bar :: bars )
				// 	case _ => bars
				// }
				val bar = input.substring(firstBar + 1, nextBar)//Don't want | in stored bar
				val rest = input.substring(nextBar)
				splitIntoBars(rest, bar :: bars)
			}else{
				bars //if no more bars, return final result
			}
		}

		
		//Edit bars so that there are no leading spaces
		
		// val barsSpaced = bars.map(
		// 	bar => evenWhiteSpace(bar.dropWhile(_ == ' '))//drop leading whitespace first
		// )

		def splitIntoChords(input: String, chords: List[String]): List[String] ={	
			//Remove leading whitespace so chord will start at pos 0 of string
			val inputNoLead = input.dropWhile(_ == ' ')


			val firstSpace = inputNoLead.indexOf(' ')

			if (inputNoLead.length > 0){
				val chord = if (firstSpace != -1){
					inputNoLead.substring(0, firstSpace)//doesn't include whitespace	
				} else{
					inputNoLead.substring(0) // If no space at end, just take the entire string
				}

				val rest = if (firstSpace != -1)
				{inputNoLead.substring(firstSpace)}else{
					"" //if no spaces left, chord will alread have been taken and no more chords left to take
				}

				splitIntoChords(rest, chord :: chords)
			}else{
				chords
			}	
		}

		val barStrings = splitIntoBars(raw, List[String]()).reverse

		val bars = barStrings.map(
			barString => Bar(splitIntoChords(barString, List()))
		)

		bars
	}


	def formatSong(song: Song): String = {//Two mutables

		val bars = parseText(song.content)
		val longestChord = bars.foldLeft(0)((b, bar) => 
		{
			val longestChordInBar = bar.chords.foldLeft(0)((c, chord) =>
				if(chord.length > c){
					chord.length
				}else{
					c
				}
			)
			if (longestChordInBar > b){
				longestChordInBar
			}else{
				b
			}
		}
		)
		//Format like this
		//   |111-222-333-444-| - = space, *** = chord

		//One chord
		val template = "|" + ("1"*longestChord + " ") +
			("2"*longestChord + " ") +
			("3"*longestChord + " ") +
			("4"*longestChord + " ") 


		var barStrings = List[String]() //!!!! MUTABLE STATE

		def padWhiteSpace(chord: String) = {
			chord + " "*(longestChord-chord.length)
		}

		bars.foreach(//Pretty ugly right now
			bar => {
				val numChords = bar.chords.length
				val barString = numChords match {
					case 1 => {
						template.replace("1"*longestChord,padWhiteSpace(bar.chords(0))).
						replace("2"*longestChord," "*longestChord).
						replace("3"*longestChord," "*longestChord).
						replace("4"*longestChord," "*longestChord)
					}
					case 2 => {
						template.replace("1"*longestChord,padWhiteSpace(bar.chords(0))).
						replace("2"*longestChord," "*longestChord).
						replace("3"*longestChord,padWhiteSpace(bar.chords(1))).
						replace("4"*longestChord," "*longestChord)
					}
					case 3 => {
						template.replace("1"*longestChord,padWhiteSpace(bar.chords(0))).
						replace("2"*longestChord," "*longestChord).
						replace("3"*longestChord,padWhiteSpace(bar.chords(1))).
						replace("4"*longestChord,padWhiteSpace(bar.chords(2)))
					}
					case 4 => {
						template.replace("1"*longestChord,padWhiteSpace(bar.chords(0))).
						replace("2"*longestChord,padWhiteSpace(bar.chords(1))).
						replace("3"*longestChord,padWhiteSpace(bar.chords(2))).
						replace("4"*longestChord,padWhiteSpace(bar.chords(3)))
					}
					case 0 => {
						template.replace("1"*longestChord," "*longestChord).
						replace("2"*longestChord," "*longestChord).
						replace("3"*longestChord," "*longestChord).
						replace("4"*longestChord," "*longestChord)
					}
					case _ => {
						template
					}
				}
				barStrings = barString :: barStrings
			}

		)
		var finalPrintOut = ""

		for (i <- 0 until barStrings.length){
			//string measures together, newline every four measures
			finalPrintOut += (if (i % 4 == 3){barStrings(i) + '\n'} else { barStrings(i)})
		}

		finalPrintOut
	}

}



val song = Song("|Cmaj7 Am7| Dm7 G7| E7 Am7 | Dm7 G7b9| Gm7 F#m7 Ab7 G6| C | D | E | F | G | G | G D | F F D |", 1, None, None)

print(ChordM.formatSong(song))




