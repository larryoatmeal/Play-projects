package models

import anorm._
import anorm.SqlParser._
import play.api.Play.current
import play.api.db.DB
import play.api.Logger

case class PurchaseM(productid: Int,  quantity: Int, customerID: Int)
case class ReceiptDisplay(id: Int, firstName: String = "FIRST", lastName: String = "SECOND", productName: String, quantity: Int, customerID: Int, price: Int)

object PurchaseM{


	def add(order: PurchaseM) = DB.withConnection {
		implicit connection =>

		val sqlCommand = SQL("""INSERT INTO purchase (productid, quantity,customerid)
				VALUES ({productid},{quantity},{customerid})
			""").on(
			"productid" -> order.productid,
			"quantity" -> order.quantity,
			"customerid" -> order.customerID
			)

		sqlCommand.executeUpdate() == 1

	}

	def duplicates(id: Int) = DB.withConnection{
		implicit connection =>

		val listOfPrimaryKey = SQL("SELECT customerid FROM purchase")().map (
				row => row[Int]("customerid")
		).toList

		listOfPrimaryKey.contains(id)
	}

	def joinProductPurchase = DB.withConnection{
		implicit connection =>

		//Match products with purchases
		//Then match purchases with the users who purchased

		val joinedList = SQL("""

			SELECT products.id, products.name, 
			purchase.quantity, purchase.customerid, products.price,
			user.firstName, user.lastName
			FROM products
			JOIN purchase
			ON products.id = purchase.productid
			JOIN user
			ON purchase.customerid = user.user_id
			ORDER BY products.id
			""")().map(

			row => ReceiptDisplay(
				row[Int]("products.id"),
				row[String]("user.firstName"),
				row[String]("user.lastName"),
				row[String]("products.name"),
				row[Int]("purchase.quantity"),
				row[Int]("purchase.customerid"),
				row[Int]("products.price")
				)

			).toList
		joinedList

	}
}