package chorder

import scala.util.matching.Regex


object Chord{

	val Maj = 1
	val Min = 2
	val Aug = 3
	val Dim = 4
	val Maj7 = 5
	val Min7 = 6
	val Dom7 = 7
	val Dim7 = 8
	val Hdim7 = 9
	val Maj9 = 10
	val Min9 = 11
	val Dom9 = 12
	val Maj11 = 13
	val Min11 = 14
	val Dom11 = 15
	val Maj13 = 16
	val Min13 = 17
	val Dom13 = 18
	val Maj6 = 19
	val Maj69 = 20
  val Min6 = 21
	val Sus2 = 22
	val Sus4 = 23
	val Sus7 = 24
  val Other = -1


	def mInterval(step: String) = {
		step match {
			case "1" => 0
			case "b2" | "#1" => 1
			case "2" => 2
			case "b3" | "#2" => 3
			case "3" | "b4"=> 4
			case "4" => 5
			case "#4" | "b5" =>  6
			case "5" => 7
			case "b6" | "#5" => 8
			case "6" => 9
			case "b7" | "#6" => 10
			case "7" => 11
			case "b9" => 1
			case "9" => 2
			case "#9" => 3
			case "b11" => 4
			case "11" => 5
			case "#11" => 6
			case "b13" => 8
			case "13" => 9
			case "#13" => 10
      case _ => 0
		}
	}

	def chordize(chordS: String) = {

    import chorder.Definition.NONE
    val OTHER = "Other" //Flag for suffix


		//Root
		val rootPattern = new Regex("""^[aAbBcCdDeEfFgG][#b]?[#b]?""")
		val rootString = rootPattern findFirstIn chordS
    val root = Definition.normalize(rootString.getOrElse(NONE))

    //Bass
		val bassPattern = new Regex("""(?<=/)[aAbBcCdDeEfFgG][#b]?[#b]?""")
		val bassString = bassPattern findFirstIn chordS //contains slash
    val bass = Definition.normalize(bassString.getOrElse(NONE))

    //Suffix
    val suffix = rootString match {
      case Some(r) => bassString match {
        case Some(b) => { //root and bass
          val suffixPatternB = new Regex(s"""(?<=$r)[^\n]*(?=/$b)""")
          (suffixPatternB findFirstIn chordS).getOrElse(OTHER)


        }
        case None => { //root, no bass
          val suffixPatternNoB = new Regex(s"""(?<=$r)[^\n]*""")
          (suffixPatternNoB findFirstIn chordS).getOrElse(OTHER)
        }
      }

      case None => (OTHER)
    }

    //Split suffix into kind and extension
    val kindDictionary = List(
      ("M", Maj) , ("Maj", Maj) ,
      ("m", Min) , ("min", Min) , ("-", Min),
      ("aug", Aug), ("+", Aug),
      ("dim", Dim),
      ("Maj7", Maj7), ("M7", Maj7), ("maj7", Maj7),
      ("m7", Min7) , ("min7", Min7) , ("-7", Min7),
      ("7", Dom7), 
      ("dim7", Dim7), ("d7", Dim7),
      ("hdim7", Hdim7), ("m7b5", Hdim7),
      ("Maj9", Maj9), ("M9", Maj9), ("maj9", Maj9),
      ("m9", Min9) , ("min9", Min9) , ("-9", Min9),
      ("9", Dom9),
      ("Maj11", Maj11), ("M11", Maj11), ("maj11", Maj11),
      ("m11", Min11) , ("min11", Min11) , ("-11", Min11),
      ("11", Dom11),
      ("Maj13", Maj13), ("M13", Maj13), ("maj13", Maj13),
      ("m13", Min13) , ("min13", Min13) , ("-13", Min13),
      ("13", Dom13),
      ("M6", Maj6), ("Maj6", Maj6),
      ("69", Maj69),
      ("m6", Min6), ("-6", Min6), ("min6", Min6),
      ("sus2", Sus2),
      ("sus", Sus4), ("sus4", Sus4),
      ("sus7", Sus7)
    )
    
  
    val kindTuple = suffix match { //string + id
      case OTHER => (OTHER,Chord.Other)
      case "" => ("", Maj) //For major chords
      case s => {
        //Find all matching kinds. Anchor to beginning of suffix
        val listOfMatchingBases = kindDictionary.filter{
          case (kind, id) => {
            //Make sure to escape all literals
            val kindPattern = new Regex("""^\Q""" + kind + """\E""")
            !(kindPattern findFirstIn s).isEmpty
          }
        }

        //println(listOfMatchingBases)

        //Find chord with highest id, higher precision
        listOfMatchingBases.foldLeft((OTHER,Chord.Other)){
          case (leader, (kind, id)) => {
            if (id > leader._2){(kind, id)}
            else {(kind, leader._2)}
          }
        }
      }
    }

    val kind = kindTuple._2

    val extensions = kindTuple._1 match {
      case OTHER => List[Int]()
      case `suffix` => List[Int]() //if the suffix consists of only the base, no extensions
      case b => {
        //Remove base from string. Taken for granted base begins the string
        val extensionOnly = suffix.drop(b.length)
        //Get all extensions matching pattern b5, #4, b6, 2, 4
        //Accidental not necessary. Without accidental is like an add2 or add6
        val extensionPattern = new Regex("""[b#]?[123456789][123456789]?""")
        val extensionStrings = extensionPattern findAllIn extensionOnly
        extensionStrings.toList.map(mInterval(_))
      }

    }

    //println("r:" + root + " s:" +  suffix + " b:" + bass + " x:" + kind + " +:" + extensions)
    Tuple4(root, bass, kind, extensions.toArray)
    

  }

}



class Chord(chordS: String, beat: Int, measure: Int){

  val (root, bass, kind, extensions) = Chord.chordize(chordS)

  def notes:Array[Int] = {
    //Chord tones + any extensions. Stored as an array
    val scaleDegrees = ChordDefinitions.chordMap(kind) ++ extensions

    val rootMidi = Definition.midify(root, -1)
    for (degree <- scaleDegrees) yield {//Adding intervals to root tone
      rootMidi + degree
    }

  }

  val getRaw = chordS
  val getBeat = beat
  val getMeasure = measure
  

  override def toString = {
    s"Root: $root, Bass: $bass, Kind: $kind, Extensions: $extensions"
  }


}


