package models

import anorm._
import anorm.SqlParser._
import play.api.Play.current
import play.api.db.DB
import play.api.Logger
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
case class RenderObject(content: String, origKey: String, destKey: String, timeSig: Int)
case class ChordWithMeasure(chord: Chord, measure: Int)

object ChordM{
	//Types of bars
	val MusicBar = 1
	val TextBar = 0



	private def parseTextToBars(raw: String, origKey: String, destKey: String): List[Bar] = {

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
			//val keySigPattern = new Regex() 
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
			//Extract qualifiers out first
			val beatPattern = new Regex("""(?<=\.)[1234567890]""")
			val beat = beatPattern findFirstIn chordS

			val beatNumber = if (beat.isEmpty){None}else{
				//println("Beat")
				Some(beat.get.toInt)
			}

			//For raw string, remove beat qualifiers
			val chordS2 = beat match {
				case Some(beatS) => chordS.replace("." + beatS, "")
				case None => chordS
			}


			//a chord has three parts
			//[Root][Quality]/[Bass]
			//Regex reads from left to right, which is good for us
			val rootPattern = new Regex("""^[aAbBcCdDeEfFgG][#b]?[#b]?""") //accidentals are optional. Must occur at beginning
			val root = rootPattern findFirstIn chordS2
			val bassPattern = new Regex("""(?<=/)[aAbBcCdDeEfFgG][#b]?[#b]?""")
			val bass = bassPattern findFirstIn chordS2 //contains slash

			val quality = if (!root.isEmpty){//If there is a root
				if(bass.isEmpty){ //If no bass
					//Get entire string after root
					val qualityPatternNoB = new Regex("""(?<=""" + root.get + """)[^\n]*""")//everything after root
					qualityPatternNoB findFirstIn chordS2
				}else{//If there is a bass

					val qualityPatternB = new Regex("""(?<=""" + root.get + """)[^\n]*(?=""" + bass.get + ")")//everything after root, before bass
					qualityPatternB findFirstIn chordS2
				}  
			}else{
				None
			}

			new Chord(root, bass, quality, beatNumber, None, chordS2)
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
				transpose(chords.reverse, origKey, destKey)
			}	
		}



		val barStrings = splitIntoBars(raw, List[String]()) //just your bar strings

		val barTypes = typeOfBars(barStrings)

		val barTuples = barStrings.zip(barTypes)

		// print(barStrings)



		//Tuple2(String, Int) Contain bar and bar type
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
		barObjects
	}


	def allChords(bars: List[Bar]) = {
		
		//zip contains index too!
		val barsWithChords = bars.filter(bar => bar.barType == MusicBar).zipWithIndex

		barsWithChords.foldLeft(List[ChordWithMeasure]()){
			case (acc, (bar,indexB)) => {


				val chords = bar.chords.zipWithIndex.foldLeft(List[ChordWithMeasure]()){
					case(acc, (chord, indexC)) =>

					//Must explicity provide default beat 

					val chord2 = chord.beat match {
						case Some(beat) => chord
						case None => {

							val defaultBeat = bar.chords.length match{ 
							
								case 1 => indexC match {
									case 0 => 1        
								}
								case 2 => indexC match {//If two chords in measure
									case 0 => 1        //First chord on beat 1
									case 1 => 3        //Second chord on beat 3
								}
								case 3 => indexC match {
									case 0 => 1
									case 1 => 3
									case 2 => 4
								}
								case 4 => indexC match {
									case 0 => 1
									case 1 => 2
									case 2 => 3
									case 3 => 4
								}
							}

							Chord(chord.root, chord.bass, chord.quality, Some(defaultBeat), chord.modifier, chord.raw)
						}
					}

					ChordWithMeasure(chord2, indexB+1) :: acc
				}.reverse
				acc ::: chords
			}
		}

		//Logger.debug(allChords.toString)	
	}






	def musicXML(raw: String, origKey: String = "C", destKey: String = "C", timeSig: Int = 4, song: Song = Song(None, 1, None, None, None, None)) = {
		val bars = parseTextToBars(raw, origKey, destKey)

		// allChords(bars).foreach(
		// 	c =>
		// 	Logger.debug(c.chord.raw + ":" + c.measure + "," + c.chord.beat)

		// )

		val chords = allChords(bars)

		//Return chords to bars. We're going in circles. Very unnecessary, but good exercise
		val measures = for (chord <- chords) yield {
			chord.measure
		}
		val measureChords = measures zip chords
		val maxMeasure = measures.last

		val barlist = (for (measure <- 1 to maxMeasure) yield {//Will yield a List[Tuple2(measure, List[Chord]])
			Tuple2(measure, 
			for (measureChord <- measureChords
				if (measureChord._1 == measure)	
				) yield {
				measureChord._2.chord
			}
			)
		}).toList

		//println(barlist)



		def xmlPitch(note: Option[String]) = {
			note match {
				case Some(n) => {
					val step = n.head
					val accidental = n.filter(_ != step)
					val alter = accidental match {
						case "" => 0
						case "#" =>  1
						case "##" => 2
						case "b" => -1
						case "bb" => -2
					}
					Tuple2(step, alter.toString)}
				case None => {
					Tuple2("", "")
				}	
			}
		}

		def xmlKind(quality: Option[String]) = {

			quality match {
				case Some(q) => {
					q.toLowerCase match {
						case "" | "maj" => "major"
						case "m" => "minor"
						case "aug" | "+" => "augmented"
						case "dim" | "\u00B0" => "diminished"
						case "7" => "dominant"
						case "maj7" => "major-seventh"
						case "min7" | "m7" => "minor-seventh"
						case "dim7" | "\u00B07"=> "diminished-seventh"
						case "halfdim7" | "m7b5" | "\u00D8"=> "half-diminished"
						case "aug7" | "7#5" => "augmented-seventh"
						case "mM7" => "major-minor"
						case "6" => "major-sixth"
						case "m6" => "minor-sixth"
						case "sus2" => "suspended-second"
						case "sus4" => "suspended-fourth"
						case "9" => "dominant-ninth"
						case "maj9" => "major-ninth"
						case "min9" | "m9" => "minor-ninth"
						case "11" => "dominant-11th"
						case "maj11" => "major-11th"
						case "min11" | "m11" => "minor-11th"
						case "13" => "dominant-13th"
						case "maj13" => "major-13th"
						case "min13" | "m11" => "minor-13th"
						case _ => "other"
						case _ => "other"	
					}
				}
				case None => "none"
			}
		}


		// <?xml version="1.0" encoding="UTF-8" standalone="no"?>
		// <!DOCTYPE score-partwise PUBLIC
		// "-//Recordare//DTD MusicXML 3.0 Partwise//EN"
		// "http://www.musicxml.org/dtds/partwise.dtd">
		<score-partwise version="3.0">
			<part-list>
			<score-part id="P1">
			<part-name>Part 1</part-name>
			</score-part>
			</part-list>

			<part id="P1">
			{for(barTuple <- barlist) yield barTuple match {
				case (measure, chords) => {
					<measure number={measure.toString}>

						
							<attributes>
							{if (measure == 1)
								<divisions>1</divisions>
								<key>
								<fifths>0</fifths>
								</key>
								<time>
								<beats>4</beats>
								<beat-type>4</beat-type>
								</time>
								<clef>
								<sign>G</sign>
								<line>2</line>
								</clef>
							}
								<measure-style>
          							<slash></slash>
        						</measure-style>
							</attributes>
						
						

					{for( i <- 1 to timeSig ) yield {

						val activeChord = chords.find(chord => chord.beat.get == i)//gets first element
						val rest = activeChord.isEmpty 
						
						//Need to indicate somehow that this is first part is part of the xml
						//Add an outer tag
						<harmony>
						{if(!rest) 
						        <root>
						          <root-step>{xmlPitch(activeChord.get.root)._1}</root-step>
						          <root-alter>{xmlPitch(activeChord.get.root)._2}</root-alter>
						        </root>
						        <kind halign="center" text="">{xmlKind(activeChord.get.quality)}</kind>
						        <bass>
				          			<bass-step>{xmlPitch(activeChord.get.bass)._1}</bass-step>
				          			<bass-alter>{xmlPitch(activeChord.get.bass)._2}</bass-alter>
				       		    </bass>
					    }
					    </harmony>
						<note>
							<pitch>
								<step>C</step>
								<octave>4</octave>
							</pitch>
							<duration>1</duration>
							<type>quarter</type>
						</note>	

						// else{
						// 	<note>
						// 	<pitch>
						// 		<step>C</step>
						// 		<octave>4</octave>
						// 	</pitch>
						// 	<duration>1</duration>
						// 	<type>quarter</type>
						// 	</note>	
						// }

					}
					}

					</measure>
				}
			}
		}
			</part>
		</score-partwise>


	}


	def formatSong(raw: String, origKey: String = "C", destKey: String = "C", timeSig: Int = 4) = {//No vars! YAY. 
		val bars = parseTextToBars(raw, origKey, destKey)
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

			//println(bar.chords)

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

				//Behaviour: Tolerant. If chord beat is not within
				//Time signature, replace just won't happen
				//And you will be left with a slash chord

				val template = templateGen(timeSig)
				val chordNums = ( 0 until chordMap.length).toList

				val fillChords = chordNums.foldLeft(template){
					(t, chordNum) => {
						val chord = bar.chords(chordNum)
						val beat = chordMap(chordNum)
						t.replace(beat.toString*longestChord, padWhiteSpace(chord.raw))
					}
				}

				//List of unfilled beats
				val rests = (1 to timeSig).toList.filter(!chordMap.contains(_))

				//For the unfilled beats, replace with slash chord
				val finalBar = rests.foldLeft(fillChords){
					(t, restNum) => {
						t.replace(restNum.toString*longestChord, "/" + " "*(longestChord-1) )// Slash takes up one space
					}
				}

				finalBar
			}


			//List(1, 3, 4) Tells the first chord to be on first beat
			//Second chord to be on third beat, etc
			//If user inputed own beat, use that, otherwise use default
			val formattedBar = bar.chords.length match {
					case 1 => {
						val one = bar.chords(0).beat.getOrElse(1)


						createBar(List(one))
					}
					case 2 => {
						val one = bar.chords(0).beat.getOrElse(1)
						val two = bar.chords(1).beat.getOrElse(3)

						createBar(List(one, two))
					}
					case 3 => {
						val one = bar.chords(0).beat.getOrElse(1)
						val two = bar.chords(1).beat.getOrElse(3)
						val three = bar.chords(2).beat.getOrElse(4)

						createBar(List(one, two, three))
					}
					case 4 => {
						val one = bar.chords(0).beat.getOrElse(1)
						val two = bar.chords(1).beat.getOrElse(2)
						val three = bar.chords(2).beat.getOrElse(3)
						val four = bar.chords(3).beat.getOrElse(4)

						createBar(List(one,two, three, four))
					}
					case 0 => {
						createBar(List())
					}
					case _ => {
						templateGen(timeSig)
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

	//If destinationKey is "Roman" -> use Roman numeral
	def transpose(chords: List[Chord], currentKey: String, destinationKey: String) = {
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
		@deprecated def majorScalify(midinote: Int) = {//Get midi notes for any scale
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

		@deprecated def getMajorScale(notename: String) = {
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


		@deprecated def getChromaticScale(notename: String) = {
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


		def getRoman(note: String) = {
			val currentMidi = midify(note)
			val currentLetter = note.head.toString
			val currentKeyLetter = currentKey.head.toString

			//Interval distance (1, 2, 3, 4, 5, 6, 7, etc)
			val intervalInt = (doremiMod(currentLetter) - doremiMod(currentKeyLetter) + 7)%7 + 1
			//Actual distance
			val midiDistance = (midify(note) - midify(currentKey) + 12)%12

			//Map perfect/major intervals to midi note differences
			val intervalMap = Map(
				1 -> 0,
				2 -> 2,
				3 -> 4,
				4 -> 5,
				5 -> 7,
				6 -> 9,
				7 -> 11
			)

			val romanExtension = intervalInt match {
				case 1 | 4 | 5 => { //perfect intervals
					(midiDistance - intervalMap(intervalInt)) match { //Discrepancy between interval and perfect
						case 0 => ""
						case 1 => "#" //Up 1
						case -1 => "b" //Down 1
					}
				}
				case 2 | 3 | 6 |7 => {
					(midiDistance - intervalMap(intervalInt)) match { //Discrepancy between interval and major
						case 0 => ""
						case 1 => "#" //Up one
						case -1 => "b" //Down one
						case -2 => "bb" //Down two
					}
				}
			}

			val romanMap = Map(
				1 -> "I",
				2 -> "II",
				3 -> "III",
				4 -> "IV",
				5 -> "V",
				6 -> "VI",
				7 -> "VII"
			)

			romanExtension + romanMap(intervalInt)
		}


		

		//val differenceInSemitones = (midify(destinationKey) - midify(currentKey))
		//val noteMapping = getChromaticScale(destinationKey)



		

		def newNote(note: String) = {
			val keyMidi = midify(currentKey)
			val keyLetter = doremiMod(currentKey.head.toString)
			val newKeyMidi = midify(destinationKey)
			val newKeyLetter = doremiMod(destinationKey.head.toString)
			//Measure each note with relation to root
			//Apply same relationship to new key
			val midiDelta = midify(note) - keyMidi
			val letterDelta = doremiMod(note.head.toString) - keyLetter
			val transposedMIDI = (newKeyMidi + midiDelta + 12)%12
			val desiredLetter = reverseDoremiMod((newKeyLetter + letterDelta + 7)%7)

			findSpelling(transposedMIDI, desiredLetter)
		}
	


		val newchords = for (chord <- chords) yield {
			val newRootName = if (chord.root.isEmpty){
				None
			}else{
				if (destinationKey == "Roman"){
					Some(getRoman(chord.root.get))
				}else{
					Some(newNote(chord.root.get))
				}
			}

			val newBassName = if (chord.bass.isEmpty){
				None
			}else{
				if (destinationKey == "Roman"){
					Some(getRoman(chord.bass.get))
				}else{
					Some(newNote(chord.bass.get))
				}
			}
			
			val newraw = newRootName.getOrElse("") + chord.quality.getOrElse("") + newBassName.getOrElse("") 

			Chord(newRootName, newBassName, chord.quality, chord.beat, chord.modifier, newraw)
		}
		newchords
	}


	def getSong(song_id: Int) = DB.withConnection{
		implicit connection =>

		SQL("""
			SELECT * FROM songs
			WHERE song_id = {song_id}
			""").on("song_id" -> song_id)().map(
			row => Song(
				row[Option[String]]("content"),
				row[Int]("user_id"),
				row[Option[String]]("composer"),
				row[Option[String]]("date"),
				row[Option[Int]]("song_id"),
				row[Option[String]]("title")
				)
			).toList(0)
	}

	def newSong(user_id: Int) = DB.withConnection{
		implicit connection =>
		SQL("""
			INSERT INTO songs
			(user_id)
			VALUES({user_id})
			""").on(
			"user_id" -> user_id
			).executeUpdate() == 1
	}
	def saveSong(song: Song) = DB.withConnection{
		implicit connection =>
		SQL("""
			UPDATE songs
			set content = {content}, user_id = {user_id},
			composer = {composer}, date = {date}, title = {title}
			WHERE song_id = {song_id}
			""").on(
			"content" -> song.content,
			"user_id" -> song.user_id,
			"composer" -> song.composer,
			"date" -> song.date,
			"song_id" -> song.song_id,
			"title" -> song.title
			).executeUpdate() == 1
	}
	def deleteSong(song_id: Int) = DB.withConnection{
		implicit connection =>
		SQL("""
			DELETE FROM songs
			WHERE song_id = {song_id}
			""").on(
			"song_id" -> song_id
			).executeUpdate() == 1
	}
	def getSongs(user_id: Int) = DB.withConnection{
		implicit connection =>
		SQL("""
			SELECT * FROM songs
			WHERE user_id = {user_id}
			""").on("user_id" -> user_id)().map(
			row => Song(
				row[Option[String]]("content"),
				row[Int]("user_id"),
				row[Option[String]]("composer"),
				row[Option[String]]("date"),
				row[Option[Int]]("song_id"),
				row[Option[String]]("title")
				)
			).toList
	}


}

















/* MUTABLE TRASH
	

				//var barStrings = List[String]() //!!!! MUTABLE STATE
				// bars.foreach(
		// 	bar => {
		// 		val barString = formatMusicBar(bar)
		// 		barStrings = barString :: barStrings
		// 	}

		// )
		
		//val barsFinal = barStrings.reverse //must reverse list

		// var finalPrintOut = ""

		// for (i <- 0 until barsFormatted.length){
		// 	//string measures together, newline every four measures
		// 	finalPrintOut += (if (i % 4 == 3){barsFormatted(i) + '\n'} else { barsFormatted(i)})
		// }

		// barsFinal.foreach{
		// 	bars => {



		// 	}
		// }
	
	// val currentBassMIDI = midify(chord.bass.get) //Deal with Some/None later
			// val newBassMIDI = (currentBassMIDI + differenceInSemitones)%12
			// val newBassName = noteMapping(newBassMIDI)

	*/