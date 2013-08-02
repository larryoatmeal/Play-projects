package chorder

object ChordDefinitions{

  val Maj = Array("1", "3", "5") 
  val Min = Array("1", "b3", "5")
  val Aug = Array("1", "3", "#5")
  val Dim = Array("1", "b3", "b5")
  val Maj7 = Array("1", "3", "5", "7")
  val Min7 = Array("1", "b3", "5", "b7")
  val Dom7 = Array("1", "3", "5", "b7")
  val Dim7 = Array("1", "b3", "b5", "6")
  val Hdim7 = Array("1", "b3", "b5", "b7")
  val Maj9 = Array("1", "3", "5", "7", "9")
  val Min9 = Array("1", "b3", "5", "7", "9")
  val Dom9 = Array("1", "3", "5", "b7", "9")
  val Maj11 = Array("1", "3", "5", "7", "9", "11")
  val Min11 = Array("1", "b3", "5", "7", "9", "11")
  val Dom11 = Array("1", "3", "5", "b7", "9", "11")
  val Maj13 = Array("1", "3", "5", "7", "9", "11", "13")
  val Min13 = Array("1", "b3", "5", "b7", "9", "11", "13")
  val Dom13 = Array("1", "3", "5", "b7", "9", "11", "13")
  val Maj6 = Array("1", "3", "5", "6")
  val Maj69 = Array("1", "3", "5", "6", "9")
  val Min6 = Array("1", "b3", "5", "6")
  val Sus2 = Array("1", "2", "5")
  val Sus4 = Array("1", "4", "5")
  val Sus7 = Array("1", "4", "5", "7")
  val Other = Array("1")


  private val chordConfigs: List[Array[Int]] = List(Maj, Min, Aug, Dim, Maj7, Min7, Dom7, Dim7, Hdim7, Maj9, Min9, Dom9, Maj11, Min11, Dom11, Maj13, Min13, Dom13, Maj6, Maj69, Min6, Sus2, Sus4, Sus7, Other).map(
      config => config.map(
        degree => Chord.mInterval(degree)
      )
  )

  private val chordIds = List(Chord.Maj, Chord.Min, Chord.Aug, Chord.Dim, Chord.Maj7, Chord.Min7, Chord.Dom7, Chord.Dim7, Chord.Hdim7, Chord.Maj9, Chord.Min9, Chord.Dom9, Chord.Maj11, Chord.Min11, Chord.Dom11, Chord.Maj13, Chord.Min13, Chord.Dom13, Chord.Maj6, Chord.Maj69, Chord.Min6, Chord.Sus2, Chord.Sus4, Chord.Sus7, Chord.Other)


  private val chordTuples = chordIds zip chordConfigs

  val chordMap = chordTuples.foldLeft(Map[Int, Array[Int]]()){
    case (acc, (id, config)) => {
      acc ++ Map[Int, Array[Int]](id -> config)
    }
  }


  def printer = println(chordTuples)



}