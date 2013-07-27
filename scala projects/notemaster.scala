// //Convert letter into number
// 		def midify(notename: String) = {
// 			notename match {
// 				case "C" | "Dbb" | "B#"  => 0 
// 				case "C#"| "Db"  | "B##" => 1
// 				case "D" | "Ebb" | "C##" => 2
// 				case "D#"| "Eb"  | "Fbb" => 3
// 				case "E" | "Fb"  | "D##" => 4
// 				case "F" | "Gbb" | "E#"  => 5
// 				case "F#"| "Gb"  | "E##" => 6
// 				case "G" | "Abb" | "F##" => 7
// 				case "G#"| "Ab"   		 => 8
// 				case "A" | "Bbb" | "G##" => 9
// 				case "A#"| "Bb"  | "Cbb" => 10
// 				case "B" | "Cb"  | "A##" => 11
// 			}
// 		}

// 		//A major scale contains one of each note. Must be able to use % 
// 		val doremiMod = Map(
// 			"C" -> 0,
// 			"D" -> 1,
// 			"E" -> 2,
// 			"F" -> 3,
// 			"G" -> 4,
// 			"A" -> 5,
// 			"B" -> 6
// 		)
// 		val reverseDoremiMod = Map(
// 			0 -> "C",
// 			1 -> "D",
// 			2 -> "E",
// 			3 -> "F",
// 			4 -> "G",
// 			5 -> "A",
// 			6 -> "B"
// 		)


// 		def findSpelling(midinote: Int, letter: String) = {//get spelling for note letter and midinote number
// 			//For each char, test every permutation with that letter for the required note
// 			//Natural
// 			if (midify(letter) == midinote) {
// 				letter
// 			}//Sharp
// 			else if ((midify(letter) + 1 + 12)%12 == midinote){//Need the plus 12 to correct for how scala does mods
// 				letter + "#"
// 			}//Flat
// 			else if ((midify(letter) - 1 + 12)%12 == midinote){
// 				letter + "b"
// 			}//Double sharp
// 			else if ((midify(letter) + 2 + 12)%12 == midinote){
// 				letter + "##"
// 			}//Double flat
// 			else if ((midify(letter) - 2 + 12)%12 == midinote){
// 				letter + "bb"
// 			}else{
// 				"X"
// 			}
// 		}
// 		def majorScalify(midinote: Int) = {//Get midi notes for any scale
// 			Map ( //W W H W W W H
// 				1 -> midinote,
// 				2 -> (midinote + 2)%12,
// 				3 -> (midinote + 4)%12,
// 				4 -> (midinote + 5)%12,
// 				5 -> (midinote + 7)%12,
// 				6 -> (midinote + 9)%12,
// 				7 -> (midinote + 11)%12
// 			)
// 		}

// 		def getMajorScale(notename: String) = {
// 			//Example:
// 			//notename: Eb

// 			//midinote = midify(Eb) -> 3
// 			//scale = majorScalify(midinote) -> majorScalify(3) -> what frequencies of notes actualy being sounded
// 			//letter = E
// 			//letterNumber = 2
// 			//letters = E F G A B C D

// 			val midinote = midify(notename)
// 			val scaleMidi = majorScalify(midinote)
// 			val letter = notename.head
// 			val letterNumber = doremiMod(letter.toString)

// 			//Get scale of desired letters
// 			val letters = for(i <- 0 to 6) yield {
// 				reverseDoremiMod((letterNumber + i)%7)  
// 			}

// 			for(i <- 0 to 6) yield {
// 				//println(scaleMidi(i+1) + "," + letters(i))
// 				findSpelling(scaleMidi(i+1),letters(i))	//Now that each letter has corresponding midi value it must be, find the right accidental
// 			}
				
// 		}

// 		def getChromaticScale(notename: String) = {
// 			val midinote = midify(notename)
// 			val scaleMidi = Map(
// 				0 -> midinote,
// 				1 -> (midinote + 1)%12,
// 				2 -> (midinote + 2)%12,
// 				3 -> (midinote + 3)%12,
// 				4 -> (midinote + 4)%12,
// 				5 -> (midinote + 5)%12,
// 				6 -> (midinote + 6)%12,
// 				7 -> (midinote + 7)%12,
// 				8 -> (midinote + 8)%12,
// 				9 -> (midinote + 9)%12,
// 				10 -> (midinote + 10)%12,
// 				11 -> (midinote + 11)%12
// 			)
// 			val letter = notename.head
// 			val letterNumber = doremiMod(letter.toString)

// 			//For C as an example, I want the letters
// 			// C D D E E F F G A A B B
// 			//Which will translate to
// 			// C Db D Eb E F F# G Ab A Bb B
// 			//This is the best default
// 			//Essentially: b2, b3, #4, b6, b7
// 			//Rather than #1, #2, b5, #5 #6

// 			val letters = Map(
// 				0 -> reverseDoremiMod((letterNumber + 0)%7),
// 				1 -> reverseDoremiMod((letterNumber + 1)%7),
// 				2 -> reverseDoremiMod((letterNumber + 1)%7),
// 				3 -> reverseDoremiMod((letterNumber + 2)%7),
// 				4 -> reverseDoremiMod((letterNumber + 2)%7),
// 				5 -> reverseDoremiMod((letterNumber + 3)%7),
// 				6 -> reverseDoremiMod((letterNumber + 3)%7),
// 				7 -> reverseDoremiMod((letterNumber + 4)%7),
// 				8 -> reverseDoremiMod((letterNumber + 5)%7),
// 				9 -> reverseDoremiMod((letterNumber + 5)%7),
// 				10 -> reverseDoremiMod((letterNumber + 6)%7),
// 				11 -> reverseDoremiMod((letterNumber + 6)%7)
// 			)

// 			val finalScale = for (i <- 0 to 11) yield {
// 				findSpelling(scaleMidi(i),letters(i)) 
// 			}

// 			(0 to 11).toList.foldLeft(Map[Int,String]()){
// 				(map, index) =>
// 				map ++ Map(scaleMidi(index) -> finalScale(index))
// 			}


// 			// val noteMap = Map( //Maps midi note value to display note
// 			// 	scaleMidi(0) -> finalScale(0)


// 			// )

// 			//map note name to letter


// 		}



// 		// println(getMajorScale("C#"))
// 		// println(getMajorScale("D#"))
// 		// println(getMajorScale("E#"))
// 		// println(getMajorScale("F#"))
// 		// println(getMajorScale("G#"))
// 		// println(getMajorScale("A#"))
// 		// println(getMajorScale("B#"))
// 		// println(getMajorScale("C#"))
// 		// println(getMajorScale("Cb"))
// 		// println(getMajorScale("Db"))
// 		// println(getMajorScale("Eb"))
// 		// println(getMajorScale("Fb"))
// 		//  println(getMajorScale("Gb"))
// 		// println(getMajorScale("Ab"))
// 		// println(getMajorScale("Bb"))
// 		//  println(getMajorScale("Cb"))
// 		//  println(getMajorScale("C"))
// 		// println(getMajorScale("D"))
// 		// println(getMajorScale("E"))
// 		// println(getMajorScale("F"))
// 		// println(getMajorScale("G"))
// 		// println(getMajorScale("A"))
// 		// println(getMajorScale("B"))


// 		// println(getChromaticScale("C#"))
// 		// println(getChromaticScale("D#"))
// 		// println(getChromaticScale("E#"))
// 		// println(getChromaticScale("F#"))
// 		// println(getChromaticScale("G#"))
// 		// println(getChromaticScale("A#"))
// 		// println(getChromaticScale("B#"))
// 		// println(getChromaticScale("C#"))
// 		// println(getChromaticScale("Cb"))
// 		// println(getChromaticScale("Db"))
// 		// println(getChromaticScale("Eb"))
// 		// println(getChromaticScale("Fb"))
// 		//  println(getChromaticScale("Gb"))
// 		// println(getChromaticScale("Ab"))
// 		// println(getChromaticScale("Bb"))
// 		//  println(getChromaticScale("Cb"))
// 		//  println(getChromaticScale("C"))
// 		// println(getChromaticScale("D"))
// 		// println(getChromaticScale("E"))
// 		// println(getChromaticScale("F"))
// 		// println(getChromaticScale("G"))
// 		println(getChromaticScale("A"))
// 		println(getChromaticScale("B"))



import scala.util.matching.Regex


//case class Chord(root: String, quality: String)
case class Song(content: Option[String], user_id: Int, composer: Option[String], date: Option[String], song_id: Option[Int], title: Option[String])
//case class Song(bars: List[Bar], user_id: Int)
/*
	| * * | * | * * * | * * * * |
	|: barline
	*: chord
*/

case class Bar(chords: List[Chord], newline: Int, barType: Int, barString: String)//want to record number of newlines and what type of bar it is
//Chords: list of chord strings. Newline: Number of newlines BarType: type of bar (music, text, etc) BarString: string for non musics
case class Chord(root: Option[String], bass: Option[String], quality: Option[String], beat: Option[Int], modifier: Option[String], raw: String)

object ChordM{
	val TimeSig = 4
	//Types of bars
	val MusicBar = 1
	val TextBar = 0



	private def parseTextToBars(raw: String): List[Bar] = {



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
				bars.reverse //if no more bars, return final result, must reverse
			}
		}

		//A bar can be musical, textual, etc. Can extend. Index must match splitIntoBars indexes
		def typeOfBars(bars: List[String]):List[Int] = {
			val textPattern = new Regex("""\([^\n]*\)""") 
			//Pattern |(Text)

			for (bar <- bars) yield {
				//Text: |(foo)
				if (!(textPattern findFirstIn bar).isEmpty){//if match with text formatting
					TextBar
				}else{
					MusicBar
				}
			}
		}

		//Strip parens For text bars
		def stripText(textBar: String) = {
			val newlines = textBar.count(_ == '\n')

			val firstOpen = textBar.indexOf('(')
			val lastClosed = textBar.lastIndexOf(')')
			textBar.substring(firstOpen+1,lastClosed)
		}
	
		

		def chordize(chordS: String) = {
			//a chord has three parts

			//[Root][Quality]/[Bass]
			//Regex reads from left to right, which is good for us
			val rootPattern = new Regex("""^[aAbBcCdDeEfFgG][#b]?[#b]?""") //accidentals are optional. Must occur at beginning
			val root = rootPattern findFirstIn chordS
			val bassPattern = new Regex("""/[aAbBcCdDeEfFgG][#b]?[#b]?""")
			val bass = bassPattern findFirstIn chordS //contains slash

			val quality = if (!root.isEmpty){//If there is a root
				if(bass.isEmpty){ //If no bass
					//Get entire string after root
					val qualityPatternNoB = new Regex("""(?<=""" + root.get + """)[^\n]*""")//everything after root
					qualityPatternNoB findFirstIn chordS
				}else{//If there is a bass

					val qualityPatternB = new Regex("""(?<=""" + root.get + """)[^\n]*(?=""" + bass.get + ")")//everything after root, before bass
					qualityPatternB findFirstIn chordS
				}  
			}else{
				None
			}
			new Chord(root, bass, quality, None, None, chordS)
		}

		//Returns list of chords
		def splitIntoChords(input: String, chords: List[Chord]): List[Chord] ={	
			//Remove leading whitespace so chord will start at pos 0 of string
			val inputNoLead = input.dropWhile(_ == ' ')

			val firstSpace = inputNoLead.indexOf(' ')

			/*****Newline busines*********

			User should be able to enter newlines
			Newlines should not be treated as chords

			If a newline line is present, take it out
			Separae function will look for newlines and record them
			*/
			val hasNewLine = input.contains('\n')

			val bar = if (hasNewLine){
				inputNoLead.filter( _ != '\n' )//remove newline
			}else{
				inputNoLead
			}


			if (bar.length > 0){
				val chord = if (firstSpace != -1){
					bar.substring(0, firstSpace)//doesn't include whitespace	
				} else{
					bar.substring(0) // If no space at end, just take the entire string
				}

				val rest = if (firstSpace != -1)
				{bar.substring(firstSpace)}else{
					"" //if no spaces left, chord will alread have been taken and no more chords left to take
				}

				splitIntoChords(rest, chordize(chord) :: chords)
			}else{
				//Else is only called if everything is done. Reverse list now
				chords.reverse
			}	
		}



		val barStrings = splitIntoBars(raw, List[String]()) //just your bar strings

		val barTypes = typeOfBars(barStrings)

		val barTuples = barStrings.zip(barTypes)

		print(barStrings)


		//Tuple2(String, Int)
		val barObjects = barTuples.map(
			barTuple => {
				val barString = barTuple._1
				val barType = barTuple._2

				if (barType == MusicBar){//If music bar, parse chords
					Bar(splitIntoChords(barString, List()), barString.count(_ =='\n'), MusicBar, barString)
				}else{//If not music chord, keep original string
					Bar(List(), barString.count(_ == '\n'), barType, stripText(barString))
				}
			}
		)



		// val barObjects = barStrings.map(
		// 	barString => Bar(splitIntoChords(barString, List()).reverse, barString.count(_ =='\n'), 0)
		// 			//must reverse chord list
		// 			//If barstring contains a newline, record it
		// )

		barObjects
	}

	def formatSong(raw: String) = {//No vars! YAY. 


		val bars = parseTextToBars(raw)
		val longestChord = bars.foldLeft(0)((b, bar) => 
		{
			val longestChordInBar = bar.chords.foldLeft(0)((c, chord) =>
				if(chord.raw.length > c){
					chord.raw.length
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

		def formatMusicBar(bar: Bar): String = {

			//Format like this
			//   |111-222-333-444-| - = space, *** = chord

			//One chord
			// val template = "|" + ("1"*longestChord + " ") +
			// ("2"*longestChord + " ") +
			// ("3"*longestChord + " ") +
			// ("4"*longestChord + " ")

			//Fill in empty space with spaces
			def padWhiteSpace(chord: String) = {
				chord + " "*(longestChord-chord.length)
			}

			val templateGen = (meter: Int) => {
				val beats = (1 to meter).toList
				"|" + beats.foldLeft(""){
					(template, beatNumber) => {
						template + beatNumber.toString * longestChord + " "
					}
				}
			}
			val template = templateGen(4)//courtesy

			def createBar(chordMap: List[Int]) = {//how long list is determines how many chords. Number determines which beat
				//Two lists match eachother. Each chord in bar must match to corresponding beat
				val template = templateGen(TimeSig)
				val chordNums = ( 0 until chordMap.length).toList

				val fillChords = chordNums.foldLeft(template){
					(t, chordNum) => {
						val chord = bar.chords(chordNum)
						val beat = chordMap(chordNum)
						t.replace(beat.toString*longestChord, padWhiteSpace(chord.raw))
					}
				}

				//List of unfilled beats
				val rests = (1 to TimeSig).toList.filter(!chordMap.contains(_))

				//For the unfilled beats, replace with slash chord
				val finalBar = rests.foldLeft(fillChords){
					(t, restNum) => {
						t.replace(restNum.toString*longestChord, "/" + " "*(longestChord-1) )// Slash takes up one space
					}
				}

				finalBar
			}


			val formattedBar = bar.chords.length match {
					case 1 => {
						createBar(List(1))
					}
					case 2 => {
						createBar(List(1, 3))
					}
					case 3 => {
						createBar(List(1, 3, 4))
					}
					case 4 => {
						createBar(List(1, 2, 3, 4))
					}
					case 0 => {
						createBar(List())
					}
					case _ => {
						template
					}
			}
			formattedBar	
		}

		// //bars formmated contains the string representation of each bar AND whether it contains a newline
		// val barsFormatted: List[Tuple2[String, Int]] = (bars.foldLeft(List[Tuple2[String, Int]]())(
		// 	(barTuples, bar) => {
		// 		Tuple2(formatMusicBar(bar), bar.newline) :: barTuples
		// 	}
		// )).reverse


		// //._1 String, ._2 Int
		// val finalPrintOut = barsFormatted.foldLeft("")(
		// 	(output, barTuple) => {
		// 		output + barTuple._1 + (if (barTuple._2>0){"|" + "\n"*barTuple._2} else {""})//add new lines and closing bar if needed
		// 	}
		// )



		val finalOutput = bars.foldLeft("")(
			(output, bar) => {

				output + (bar.barType match {
					case MusicBar => formatMusicBar(bar) + (if(bar.newline>0){"|" + "\n"*bar.newline} else {""}) //format, add newlines, closing bar
					case TextBar => bar.barString + "\n"*(bar.newline+1) //pass in as is, add a newline to preserve spacing. If user added there own newline, that's okay, there will be extra newlines
					case _ => "Error"
				})

			}
		)
		//Logger.debug("Ok")

		finalOutput
	}

	def transpose(chord: Chord, currentKey: String, destinationKey: String) = {
		//Convert letter into number
		def midify(notename: String) = {
			notename match {
				case "C" | "Dbb" | "B#"  => 0 
				case "C#"| "Db"  | "B##" => 1
				case "D" | "Ebb" | "C##" => 2
				case "D#"| "Eb"  | "Fbb" => 3
				case "E" | "Fb"  | "D##" => 4
				case "F" | "Gbb" | "E#"  => 5
				case "F#"| "Gb"  | "E##" => 6
				case "G" | "Abb" | "F##" => 7
				case "G#"| "Ab"   		 => 8
				case "A" | "Bbb" | "G##" => 9
				case "A#"| "Bb"  | "Cbb" => 10
				case "B" | "Cb"  | "A##" => 11
			}
		}

		//A major scale contains one of each note. Must be able to use % 
		val doremiMod = Map(
			"C" -> 0,
			"D" -> 1,
			"E" -> 2,
			"F" -> 3,
			"G" -> 4,
			"A" -> 5,
			"B" -> 6
		)
		val reverseDoremiMod = Map(
			0 -> "C",
			1 -> "D",
			2 -> "E",
			3 -> "F",
			4 -> "G",
			5 -> "A",
			6 -> "B"
		)


		def findSpelling(midinote: Int, letter: String): String = {//get spelling for note letter and midinote number
			//For each char, test every permutation with that letter for the required note
			//Natural
			if (midify(letter) == midinote) {
				letter
			}//Sharp
			else if ((midify(letter) + 1 + 12)%12 == midinote){//Need the plus 12 to correct for how scala does mods
				letter + "#"
			}//Flat
			else if ((midify(letter) - 1 + 12)%12 == midinote){
				letter + "b"
			}//Double sharp
			else if ((midify(letter) + 2 + 12)%12 == midinote){
				letter + "##"
			}//Double flat
			else if ((midify(letter) - 2 + 12)%12 == midinote){
				letter + "bb"
			}else{
				"X"
			}
		}
		def majorScalify(midinote: Int) = {//Get midi notes for any scale
			Map ( //W W H W W W H
				1 -> midinote,
				2 -> (midinote + 2)%12,
				3 -> (midinote + 4)%12,
				4 -> (midinote + 5)%12,
				5 -> (midinote + 7)%12,
				6 -> (midinote + 9)%12,
				7 -> (midinote + 11)%12
			)
		}

		def getMajorScale(notename: String) = {
			//Example:
			//notename: Eb

			//midinote = midify(Eb) -> 3
			//scale = majorScalify(midinote) -> majorScalify(3) -> what frequencies of notes actualy being sounded
			//letter = E
			//letterNumber = 2
			//letters = E F G A B C D

			val midinote = midify(notename)
			val scaleMidi = majorScalify(midinote)
			val letter = notename.head
			val letterNumber = doremiMod(letter.toString)

			//Get scale of desired letters
			val letters = for(i <- 0 to 6) yield {
				reverseDoremiMod((letterNumber + i)%7)  
			}

			for(i <- 0 to 6) yield {
				//println(scaleMidi(i+1) + "," + letters(i))
				findSpelling(scaleMidi(i+1),letters(i))	//Now that each letter has corresponding midi value it must be, find the right accidental
			}
				
		}

		def getChromaticScale(notename: String) = {
			val midinote = midify(notename)
			val scaleMidi = Map(
				0 -> midinote,
				1 -> (midinote + 1)%12,
				2 -> (midinote + 2)%12,
				3 -> (midinote + 3)%12,
				4 -> (midinote + 4)%12,
				5 -> (midinote + 5)%12,
				6 -> (midinote + 6)%12,
				7 -> (midinote + 7)%12,
				8 -> (midinote + 8)%12,
				9 -> (midinote + 9)%12,
				10 -> (midinote + 10)%12,
				11 -> (midinote + 11)%12
			)
			val letter = notename.head
			val letterNumber = doremiMod(letter.toString)

			//For C as an example, I want the letters
			// C D D E E F F G A A B B
			//Which will translate to
			// C Db D Eb E F F# G Ab A Bb B
			//This is the best default
			//Essentially: b2, b3, #4, b6, b7
			//Rather than #1, #2, b5, #5 #6

			val letters = Map(
				0 -> reverseDoremiMod((letterNumber + 0)%7),
				1 -> reverseDoremiMod((letterNumber + 1)%7),
				2 -> reverseDoremiMod((letterNumber + 1)%7),
				3 -> reverseDoremiMod((letterNumber + 2)%7),
				4 -> reverseDoremiMod((letterNumber + 2)%7),
				5 -> reverseDoremiMod((letterNumber + 3)%7),
				6 -> reverseDoremiMod((letterNumber + 3)%7),
				7 -> reverseDoremiMod((letterNumber + 4)%7),
				8 -> reverseDoremiMod((letterNumber + 5)%7),
				9 -> reverseDoremiMod((letterNumber + 5)%7),
				10 -> reverseDoremiMod((letterNumber + 6)%7),
				11 -> reverseDoremiMod((letterNumber + 6)%7)
			)

			val finalScale = for (i <- 0 to 11) yield {
				findSpelling(scaleMidi(i),letters(i)) 
			}

			(0 to 11).toList.foldLeft(Map[Int,String]()){//Maps midi notes to String  note names
				(map, index) =>
				map ++ Map(scaleMidi(index) -> finalScale(index))
			}
		}

		
		

		val differenceInSemitones = Math.abs(midify(currentKey) - midify(destinationKey))
		val currentNoteMIDI = midify(chord.root.get) //Deal with Some/None later
		val newNoteMIDI = (currentNoteMIDI + differenceInSemitones)%12
		var newNoteName = getChromaticScale(destinationKey)(newNoteMIDI)
		
	}

}









