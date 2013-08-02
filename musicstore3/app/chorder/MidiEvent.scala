package chorder


class MidiEvent(status: Int, track: Int, note: Int, delta: Int, velocity: Int){

  //Status, track
  val statusByte = MidiMaster.intToBytes((status << 4) + track, 2)
  //Note
  val noteByte = MidiMaster.noteByte(note)
  val deltaByte = MidiMaster.deltaByte(delta)
  val velocityByte = MidiMaster.velocityByte(velocity)



}