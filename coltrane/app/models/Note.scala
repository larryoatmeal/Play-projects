
package models

case class Note(noteNumber: Int)
case class Pitch(midi: Int)



object Note{
  //For convenience: Maps to midi

  //Map midi note
  private val notenameMap = Map(
    0 -> "C",
    1 -> "Db",
    2 -> "D",
    3 -> "Eb",
    4 -> "E",
    5 -> "F",
    6 -> "Gb",
    7 -> "G",
    8 -> "Ab",
    9 -> "A",
    10 -> "Bb",
    11 -> "B"
  )

  def noteName(midi: Int) = {
    val pitch = notenameMap(midi % 12) 
    val octave = midi / 12 - 1

    pitch + octave
  }

  def closestNote(have: Int, want: Int) = {
    val semitones = have % 12 - want % 12


  }



}

