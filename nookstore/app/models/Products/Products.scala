package models



case class Product (
	id: Int,
	price: Int,
	name: String,
	description: String
)

object Product {

	import anorm.SQL
	import anorm.SqlQuery
	//get products table, store in value
	val sql: SqlQuery = SQL("SELECT * FROM products")

	import play.api.Play.current
	import play.api.db.DB

	//retrieve
	def getAll: List[Product] = DB.withConnection {//create connection

		implicit connection => //make connection implicitly available

		sql().map ( row => //iterate over each row
			Product(row[Int]("id"), row[Int]("price"),
				row[String]("name"), row[String]("description"))
			//create a Product from contets of each row
			).toList // return a list

	}

	//same as getAll, using pattern matching
	def getAllWithPatterns: List[Product] = DB.withConnection{
		implicit connection =>
		import anorm.Row
		sql().collect {

			case Row(Some(id: Int), Some(price: Int),
			Some(name: String), Some(description: String)) =>
			
			Product(id, price, name, description)

		}.toList
	}

	//store
	def insert(product: Product): Boolean = {
		DB.withConnection { implicit connection =>
			SQL("""INSERT INTO products 


				""")




		}






	}




}