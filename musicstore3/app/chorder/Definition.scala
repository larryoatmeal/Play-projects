

package chorder




//m for midi
//s for score/syntax

object Definition{


  val C = "C"
  val Db = "Db"
  val D = "D"
  val Eb = "Eb"
  val E = "E"
  val F = "F"
  val Gb = "Gb"
  val G = "G"
  val Ab = "Ab"
  val A = "A"
  val Bb = "Bb"
  val B = "B"
  val NONE = "NONE" //for nulls

  def normalize(notename: String) = {
    notename match {
      case "C" | "Dbb" | "B#"  => C 
    	case "C#"| "Db"  | "B##" => Db
    	case "D" | "Ebb" | "C##" => D
    	case "D#"| "Eb"  | "Fbb" => Eb
    	case "E" | "Fb"  | "D##" => E
    	case "F" | "Gbb" | "E#"  => F
    	case "F#"| "Gb"  | "E##" => Gb
    	case "G" | "Abb" | "F##" => G
    	case "G#"| "Ab"   		   => Ab
    	case "A" | "Bbb" | "G##" => A
    	case "A#"| "Bb"  | "Cbb" => Bb
    	case "B" | "Cb"  | "A##" => B	
      case NONE                => NONE
    }
  }

  def midify(note: String, octave: Int) = {
    val octaveMinusOne = Map(
      C -> 0,
      Db -> 1,
      D -> 2,
      Eb -> 3,
      E -> 4,
      F -> 5,
      Gb -> 6,
      G -> 7,
      Ab -> 8,
      A -> 9,
      Bb -> 10,
      B -> 11,
      NONE -> 0
    )
    octaveMinusOne(note) + (octave+1) * 12 //returns real midi value
  }

}


