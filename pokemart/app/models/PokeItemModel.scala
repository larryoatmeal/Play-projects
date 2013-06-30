package models

case class PokeItemModel(price: Int, name: String, description:String, id:Int)

object PokeItemModel {

	var inventoryList = Set(
		PokeItemModel(100, "Pokeball", "Catch pokemon with this", 0),
		PokeItemModel(200, "Potion", "Restore 20HP", 1),
		PokeItemModel(99999, "Bike", "Bike with this", 2),
		PokeItemModel(500, "Fishing Rod", "Get Magikarp", 3)


		)

	def getInventory = inventoryList.toList
	def addToList(item: PokeItemModel){
		inventoryList = inventoryList + item
	}

}

case class AnOrder(Select: String, Quantity: Int )	

object AnOrder{

	var orderList = Set(AnOrder("Free Coupon", 0 ))

	def addToList(aOrder: AnOrder){

		orderList = orderList + aOrder
	}

	def getOrderList = orderList.toList

}
