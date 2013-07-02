package controllers

import play.api._
import play.api.mvc._
import models._
import play.api.data._
import play.api.data.Forms._
import scala.util.Random
object ProductC extends Controller {
  
 /* val tempList = List(
  	ProductM("Piano", 100, 0),
  	ProductM("Trombone", 500, 1),
  	ProductM("Xylophone", 300, 2)
  	)*/

  def inventory = Action {
  	//Logger.info(tempList.toString)
    //Get list of products from database
    Ok(views.html.inventory(ProductM.listOfProducts))
  }
  
  //Form takes Mapping[Object] as a paramater
  /*val productForm = Form(
  	mapping(
  		"name" -> text,
  		"price" -> number,
  		"id" -> number
  	)(ProductM.apply)(ProductM.unapply)
  )*/
  


  //custom construction. Don't need ID input
  val productForm = Form(
  	mapping (
  		"name" -> text,
  		"price" -> number
  	)
  	((name, price) => ProductM(name, price,ProductM.randomID))
  	((product: ProductM) => Some(product.name, product.price))
  )


  //GET
  def sell = Action {
    //Logger.info(ProductM.lastID.toString)
  	Ok(views.html.sellproduct(productForm))

  }

  //POST
  def addToList = Action { implicit request =>
  	productForm.bindFromRequest.fold(
  		//Fail, resend form, repopulate fields
  		formWithErrors => Ok(views.html.sellproduct(formWithErrors)),
  		//Succes
  		value => {
  			ProductM.insert(value)
        ProductM.update
  			//Ok("Created: " + value)
        Ok(views.html.inventory(ProductM.listOfProducts))
  		}
  	)
  }



}