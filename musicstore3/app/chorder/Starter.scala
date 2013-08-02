package chorder 




object Starter{
 
  def main(args: Array[String]) {

    // println(Definition.midify(Definition.C, 4))
    // val chords = args.map{
    //   new Chord(_, 4, 1)
    // }

    // chords.foreach(chord => println(chord.notes.toList))
    // println(MidiMaster.numberOfTracks(12).toList)
    // println(MidiMaster.intToBytes(0x0006, 2).toList)

    args.foreach{ arg =>
        //println(MidiMaster.intToBytes(arg.toInt, 2).toList)
        MidiMaster.deltaByte(arg.toInt).foreach{ 
                b => 
                println(MidiMaster.toHex(b))

        }
        println("")    
    }

    //println(MidiMaster.intToBytes(192, 2).toList)

    // args.foreach{
    //   argument =>
    //   ChordDefinitions.chordMap(argument.toInt).foreach{
    //     degree =>
    //     print(degree.toHexString + "-")

    //   }
    //   println("-------------------------------")

    // }
    //ChordDefinitions.chordMap(Chord.Maj).foreach(println)
    
  }
	
}