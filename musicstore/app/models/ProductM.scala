package models

import anorm._
import anorm.SqlParser._
import play.api.Play.current
import play.api.db.DB

case class ProductM(name: String, price: Int, id: Int)

object ProductM{

	/*val product = {
		get[String]("name") ~
		get[Int]("price") ~
		get[Int]("id") map {
			case name~price~id => ProductM(name, price, id)
		}
	}*/

	//so you don't have to call retrieveAll all the time
	var listOfProducts = retrieveAll()


	def retrieveAll(): List[ProductM] = DB.withConnection {

		implicit connection =>
		//sql() -> Stream[sqlRow]
		//research what map does
		val sqlCommand = SQL("SELECT * FROM products")
		sqlCommand().map ( row =>
			ProductM(row[String]("name"),
				row[Int]("price"),
				row[Int]("id")
			)
		).toList
	}

	def insert (product: ProductM) = {
		DB.withConnection { implicit connection =>
			//SQL command for inserting:
			// INSERT INTO table_name VALUES ()
			//{} -> paramaters, filled with .on()
			SQL("""INSERT INTO products
				   (id, name, price)
			       VALUES ({id}, {name}, {price})
				""").on(
				"id" -> product.id,
				"name" -> product.name,
				"price" -> product.price
			).executeUpdate() == 1
		//executeUpdate returns rows affected
		//should  be one
		//so should return true
		}
	}

	def update(){
		//renew list of products
		listOfProducts = retrieveAll()
	}

	def delete () {}

	def randomID(): Int = {
		import scala.util.Random
		val random = new Random();
		val randomInt = random.nextInt.abs
		for ( product <- listOfProducts 
			if (product.id == randomInt)
			){
			//if ID matches, call randomID again
			//Danger of infinite recursive if 
			//all ints are taken
			return randomID()
		}
		//otherwise, just send over randomInt
		randomInt
	}
}