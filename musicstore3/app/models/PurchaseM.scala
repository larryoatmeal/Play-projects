package models

import anorm._
import anorm.SqlParser._
import play.api.Play.current
import play.api.db.DB
import play.api.Logger

case class PurchaseM(productid: Int, firstName: String, lastName: String, quantity: Int, email: Option[String], customerID: Int)
case class ReceiptDisplay(id: Int, productName: String, firstName: String, lastName: String, quantity: Int, customerID: Int, price: Int)

object PurchaseM{



	def add(order: PurchaseM) = DB.withConnection {

		implicit connection =>

		val sqlCommand = order.email match {

			case Some(e) => 


			SQL("""INSERT INTO purchase (productid, firstName, lastName, quantity, email, customerid)
				VALUES ({productid},{firstName},{lastName},{quantity},{email},{customerid})
			""").on(
			"productid" -> order.productid,
			"firstName" -> order.firstName,
			"lastName" -> order.lastName,
			"quantity" -> order.quantity,
			"email" -> order.email,
			"customerid" -> order.customerID
			)
			case None =>

			SQL("""INSERT INTO purchase (productid, firstName, lastName, quantity, customerid)
				VALUES ({productid},{firstName},{lastName},{quantity},{customerid})
			""").on(
			"productid" -> order.productid,
			"firstName" -> order.firstName,
			"lastName" -> order.lastName,
			"quantity" -> order.quantity,
			"customerid" -> order.customerID
			)
		}
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

		val joinedList = SQL("""

			SELECT products.id, products.name, purchase.firstName, purchase.lastName,
			purchase.quantity, purchase.customerid , products.price
			FROM products
			JOIN purchase
			ON products.id = purchase.productid
			ORDER BY products.id
			""")().map(

			row => ReceiptDisplay(
				row[Int]("products.id"),
				row[String]("products.name"),
				row[String]("purchase.firstName"),
				row[String]("purchase.lastname"),
				row[Int]("purchase.quantity"),
				row[Int]("purchase.customerid"),
				row[Int]("products.price")
				)

			).toList

		joinedList

	}


}