import anorm._
import play.api.db._
import anorm.SqlParser._
case class Employee(id: Pk[Int] = NotAssigned, email: String, password:String)