package models

import anorm._
import anorm.SqlParser._
import play.api.Play.current
import play.api.db.DB
import play.api.Logger

case class ProductM(name: String, price: Int, id: Int, img: String)

object ProductM{

	/*val product = {
		get[String]("name") ~
		get[Int]("price") ~
		get[Int]("id") map {
			case name~price~id => ProductM(name, price, id)
		}
	}*/

	//so you don't have to call retrieveAll all the time
	//var listOfProducts = retrieveAll()
	val productsPerPage = 5

	//GLOBAL VARIABLES: Eventually have these in session data, not model
	var currentSortType = "name"
	var currentPage = 1


	//*******************************************************
	//MYSQL Communication
	def retrieveAll(): List[ProductM] = DB.withConnection {

		implicit connection =>
		//sql() -> Stream[sqlRow]
		//research what map does
		//val sqlCommand = SQL("SELECT * FROM products")

		val sqlCommand = SQL("SELECT * FROM products ORDER BY " + currentSortType)

		sqlCommand().map ( row =>
			ProductM(row[String]("name"),
				row[Int]("price"),
				row[Int]("id"),
				row[String]("img")
			)
		).toList
	}

	def retrieveSome(): List[ProductM] = DB.withConnection {

		implicit connection =>
		//sql() -> Stream[sqlRow]
		//research what map does
		//val sqlCommand = SQL("SELECT * FROM products")

		val sqlCommand = SQL("SELECT * FROM products ORDER BY " + currentSortType
			+ " LIMIT " + ((currentPage-1)*productsPerPage).toString
			 + " , " + productsPerPage.toString)

		sqlCommand().map ( row =>
			ProductM(row[String]("name"),
				row[Int]("price"),
				row[Int]("id"),
				row[String]("img")
			)
		).toList
	}

	def retrieveOne(id: Int): ProductM = DB.withConnection {
		implicit connection =>

		val sqlCommand = SQL("""SELECT * FROM products
			WHERE id = {id}
			""").on("id" -> id)

		val list = sqlCommand().map ( row =>
			ProductM(row[String]("name"),
				row[Int]("price"),
				row[Int]("id"),
				row[String]("img")
			)
		).toList

		val singleProduct = list(0)

		singleProduct
	}


	def insert (product: ProductM) = {
		DB.withConnection { implicit connection =>
			//SQL command for inserting:
			// INSERT INTO table_name VALUES ()
			//{} -> paramaters, filled with .on()
			SQL("""INSERT INTO products
				   (id, name, price, img)
			       VALUES ({id}, {name}, {price}, {img})
				""").on(
				"id" -> product.id,
				"name" -> product.name,
				"price" -> product.price,
				"img" -> product.img
			).executeUpdate() == 1
		//executeUpdate returns rows affected
		//should  be one
		//so should return true
		}
	}

	def update(product: ProductM){
		Logger.info(product.id.toString)

		DB.withConnection { implicit connection =>
			SQL("""UPDATE products
				SET name= {name}, price = {price}, img = {img}
				WHERE id = {id}
				"""
				).on(
				"id" -> product.id,
				"name" -> product.name,
				"price" -> product.price,
				"img" -> product.img
				).executeUpdate() == 1
		}
	}


	def delete (id: Int) {
		DB.withConnection{ implicit connection =>
		SQL("DELETE FROM PRODUCTS WHERE id = {id}").
		on("id" -> id).executeUpdate() == 0
		}
	}

	def numberOfItems = {
		DB.withConnection{ implicit connection =>
			val sqlCommand =SQL("SELECT COUNT(*) FROM products")
			//sqlCommand().head[Long][].toString
			sqlCommand().map {row=>
				row[Long]("COUNT(*)") //Long is type, COUNT(*) is name in MySQL
			}.toList(0)
		}
	}
	//SQL() -> Stream[SqlRow]
	//Stream is a lazy list. Part of scala
	//Stream[SqlRow].map maps row to to whatever
	//Stream.head returns first item of Stream


	//***************************************************
	//Helper methods

	def randomID(): Int = { // In efficient
		import scala.util.Random
		val random = new Random();
		val randomInt = random.nextInt.abs
		for ( product <- retrieveAll 
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


















	/**
	def retrieveAllSorted(sorttype: String): List[ProductM] = DB.withConnection {

		implicit connection =>
		//sql() -> Stream[sqlRow]
		//research what map does
		val sqlQuery = "SELECT * FROM products ORDER BY " + sorttype

		val sqlCommand = SQL(sqlQuery)

		val list = sqlCommand().map ( row =>
			ProductM(row[String]("name"),
				row[Int]("price"),
				row[Int]("id"),
				row[String]("img")
			)
		).toList

		list
	}
	**/

	


}