package models

case class SeventhIntervals(third: Int, fifth: Int, seventh: Int)


object Seventh{

  //These are intervals
  import Interval._

  val Maj7 = SeventhIntervals(M3, P5, M7)
  val Min7 = SeventhIntervals(m3, P5, m7)
  val Dom7 = SeventhIntervals(M3, P5, m7)
  val Dim7 = SeventhIntervals(m3, d5, d7)
  val Hdim7 = SeventhIntervals(m3, d5, m7)

  //Inversions
  val First = 1
  val Second = 2
  val Third = 3

  //Number of notes
  val NumberOfNotes = 4
}

class Seventh(root:Int, intervals: SeventhIntervals){


  def closedInversion(inversion: Int) = {
    val (bottom, top) = closedRoot.splitAt(inversion)
    //transpose botom notes octave up
    top ++ bottom.map(_+12)
  }

  val closedRoot = Array(root, 
    root+intervals.third,
    root+intervals.fifth,
    root+intervals.seventh)

  def drop2(inversion: Int) = {
    //Find closed inversion
    //Drop second highest note
    
    val closed = closedInversion((inversion + 2)%4)
    Array(closed(2) - 12, closed(0), closed(1), closed(3))

  }
  def drop3(inversion: Int) = {
    //Find closed inversion
    //Drop second highest note
    
    val closed = closedInversion((inversion + 3)%4)
    Array(closed(1) - 12, closed(0), closed(2), closed(3))

  }


}