package models

import anorm._
import anorm.SqlParser._
import play.api.Play.current
import play.api.db.DB
import play.api.Logger

//case class Chord(root: String, quality: String)
case class Song(content: Option[String], user_id: Int, composer: Option[String], date: Option[String], song_id: Option[Int], title: Option[String])
//case class Song(bars: List[Bar], user_id: Int)
/*
	| * * | * | * * * | * * * * |
	|: barline
	*: chord
*/

case class Bar(chords: List[String], newline: Boolean)


object ChordM{
	val TimeSig = 4



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
				bars //if no more bars, return final result
			}
		}

				
		//Returns list of chords
		def splitIntoChords(input: String, chords: List[String]): List[String] ={	
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

				splitIntoChords(rest, chord :: chords)
			}else{
				chords
			}	
		}
		
		val barStrings = splitIntoBars(raw, List[String]()).reverse //just your bar strings

		val barObjects = barStrings.map(
			barString => Bar(splitIntoChords(barString, List()).reverse, barString.contains('\n'))
					//must reverse chord list
					//If barstring contains a newline, record it
		)
		barObjects
	}

	def formatSong(raw: String) = {//No vars! YAY. 

		val bars = parseTextToBars(raw)
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

		def formatBar(bar: Bar): String = {

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
						t.replace(beat.toString*longestChord, padWhiteSpace(chord))
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

		//bars formmated contains the string representation of each bar AND whether it contains a newline
		val barsFormatted: List[Tuple2[String, Boolean]] = (bars.foldLeft(List[Tuple2[String, Boolean]]())(
			(barTuples, bar) => {
				Tuple2(formatBar(bar), bar.newline) :: barTuples
			}
		)).reverse


		//._1 String, ._2 Boolean
		val finalPrintOut = barsFormatted.foldLeft("")(
			(output, barTuple) => {
				output + barTuple._1 + (if (barTuple._2){"|\n"} else {""})//add new line and closing bar if needed
			}
		)

		finalPrintOut
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
		// 		val barString = formatBar(bar)
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


	*/