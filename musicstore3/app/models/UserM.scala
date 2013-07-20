package models
import play.api.Logger
import anorm._
import anorm.SqlParser._
import play.api.db.DB
import play.api.Play.current

case class UserM(user_id: Int, firstName: String, lastName: String,
	creditCard: Int, email: String, password: String,
	address: Option[String])



object UserM{

	//Check if username/password match
	def authenticate(email: String, password: String): Boolean = DB.withConnection{

		implicit connection =>
		val sqlCommand = SQL(s"""SELECT COUNT(email) FROM user
		WHERE email = '$email' AND password = '$password' 
		""")

		//Logger.debug(sqlCommand.toString)

		if (sqlCommand().map(
			row => row[Long]("COUNT(email)")
			).toList(0) == 1){
			true
		}else{
			false
		}
		/*
		if(email == "Larry" && password == "password"){
			Logger.debug("True")
			true

		}else{						
			Logger.debug("False")

			false
		}
		*/
	}

	def getUser(email: String): UserM = DB.withConnection{
		implicit connection =>
		SQL(s"SELECT * FROM user WHERE email = '$email'")().map(
			row => UserM(
				row[Int]("user_id"),
				row[String]("firstName"),
				row[String]("lastName"),
				row[Int]("creditCard"),
				row[String]("email"),
				row[String]("password"),
				row[Option[String]]("address")
				)
			).toList(0)
	}

	def getUser(user_id: Int): UserM = DB.withConnection{
		implicit connection =>
		SQL(s"SELECT * FROM user WHERE user_id = '$user_id'")().map(
			row => UserM(
				row[Int]("user_id"),
				row[String]("firstName"),
				row[String]("lastName"),
				row[Int]("creditCard"),
				row[String]("email"),
				row[String]("password"),
				row[Option[String]]("address")
				)
			).toList(0)
	}



	def addUser(user: UserM) = DB.withConnection{
		implicit connection =>

		SQL("""INSERT INTO user
			(firstName, lastName, creditCard, email, password, address)
			VALUES
			({firstName}, {lastName}, {creditCard}, {email}, {password}, {address})
			""").on(
			"firstName" -> user.firstName,
			"lastName" -> user.lastName,
			"creditCard" -> user.creditCard,
			"email" -> user.email,
			"password" -> user.password,
			"address" -> user.address
			).executeUpdate() == 1
	}
}


