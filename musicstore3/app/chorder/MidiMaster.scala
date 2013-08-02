package chorder


object MidiMaster{
  //These are unsigned!
  def intToBytes(n: Long, numBytes: Int = 2) = {
    (0 until numBytes).foldLeft(Array[Byte]()){
      //(acc, num) => Array[Byte]((n / Math.pow(16, num*2) % 256).toByte) ++ acc
      (acc, num) => Array[Byte]((n / Math.pow(16, 2*num).toInt %256).toByte) ++ acc
    }
  }



  val MidiHeader = Array[Byte](0x4D, 0x54, 0x68, 0x64)
  val MidiHeaderLength = Array[Byte](0x00, 0x00, 0x00, 0x06)
  val MidiFormatSingle = Array[Byte](0x00, 0x00)
  val MidiFormatMultiple = Array[Byte](0x00, 0x01)
  val MidiFormatAsync = Array[Byte](0x00, 0x02)
  
  //Status messages: First 4 bits. Must shift over 4
  val NoteOff = 0x8
  val NoteOn = 0x9
  val Aftertouch = 0xA

  //Note 
  def noteByte(note: Int) = {
    intToBytes(note, 2)
  }
  def velocityByte(velocity: Int) = {
    intToBytes(velocity, 2)
  }




  val TrackHeader = Array[Byte](0x4D, 0x54, 0x72, 0x6B)

  def deltaByte(n: Int) = {
    //Can be up to four bits
    //Not last bit is in form 1xxxxxxx (2^8)
    //Last bit is in form  0xxxxxxx
    //First get into array of bits
    require (n <= 0x0FFFFFFF)
    //4 bytes max, -4 bits to make it variable length. so 28
    val bits = ( 0 until 28).foldLeft(Array[Int]()){
      (acc, place) => {
       Array(
        if ( (n & (1 << place)) == 0){0} 
        else{1}
       ) ++ acc
       //  101100110001000
       //  000000000000001  &
       //  000000000000010  &
       //  000000000000100  & ,etc
      }
    }

    //Divide bits into groups of 7
    //First bit only tells of if it is the end of delta (0) or not (1)

    val group4 = bits.slice(0,7)
    val group3 = bits.slice(7,14)
    val group2 = bits.slice(14, 21)
    val group1 = bits.slice(21, 28)

    //Find first group that we want to use in our delta
    //We can't just test if each group contains 0s or not because they could
    //still be siginificant digits
    val groupTuples = List((group4, 4), (group3, 3), (group2, 2), (group1, 1))
    val leadingGroupIndex = groupTuples.foldLeft(1){
      case (highestValid, (group, index)) => {
        if (group.contains(1) && index > highestValid){
          index
        }else{
          highestValid
        }
      }
    }

    //Transform 7 bit to 8 bit byte with leading byte being 0 if last or 1 if not last
    //Add in order from 4 to 1
    val finalBitArray = groupTuples.foldLeft(Array[Int]()){
      case (acc, (group, index)) => {
        if (index > leadingGroupIndex){//Ignore, don't add to array
          acc
        }else if (index == 1){//If last group add 0 to beginning. If last and first, still last!
          acc ++ (Array(0) ++ group)
        }else{//Not last, add 1
          acc ++ (Array(1) ++ group)
        }
      }
    }
    //println(finalBitArray.toList)

    //Finally, convert to int then array of bytes

    //Want 0 index to match up with 0th binary place. Need Long, or rounding/cap errors
    val integerRepresentation = finalBitArray.reverse.
      zipWithIndex.foldLeft(0.toLong){
        case (acc, (value, index)) =>{
          //println(value * Math.pow(2, index).toLong)
          acc + value * Math.pow(2, index).toLong
        }
    }
    //println(integerRepresentation)
    //println(leadingGroupIndex)
    //println(intToBytes(192, 1).toList)
    //intToBytes(integerRepresentation,1)//tells us number of bits we want

    //println(integerRepresentation)
    //println(leadingGroupIndex)

    intToBytes(integerRepresentation, leadingGroupIndex)
  }



  def toHex(byte: Byte) = {
    String.format("%02x", java.lang.Byte.valueOf(byte))
  }
}

class MidiMaster(ticks: Int, numTracks: Int){
  require (numTracks <= 0xFFFF)
  require (ticks <= 0x7FFF)


  val numberOfTracks = MidiMaster.intToBytes(ticks, 2)

  val ticksPerQuarterNote = MidiMaster.intToBytes(numTracks, 2)

  val Quarter = ticks
  val Half = ticks * 2
  val DottedQuarter = ticks * 3
  val Whole = ticks * 4
  val Eighth = ticks / 2
  val DottedEigth = ticks /4 * 3
  val Sixteenth = ticks / 4

}