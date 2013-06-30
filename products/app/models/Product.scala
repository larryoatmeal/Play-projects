package models

case class Product(
	ean: Long, name:String, description: String)

object Product{

	//Sets: Iterables with no duplicate elements
	val products = Set(
		Product(5010255079763L, "Pokeballs",
			"For catching pokemon"),
		Product(4890234582346L, "Potion",
			"For restoring pokemon"),
		Product(2478926245845L, "Repel",
			"For keaping pokemon away")
	)

	def findAll = this.products.toList.sortBy(_.ean)
    def findByEan(ean: Long) = this.products.find(_.ean == ean)
}