package models

case class Pokemon(
	number: Long, name:String, attribute: String

	)

object Pokemon{

	val pokedex = Set(
		Pokemon(1, "Bulbasaur", "Grass"),
		Pokemon(4, "Charmander", "Fire"),
		Pokemon(7, "Squirtle", "Water")
		)

	def findAll = this.pokedex.toList
	def findByNumber(number: Long) = 
	this.pokedex.find(_.number == number)



}