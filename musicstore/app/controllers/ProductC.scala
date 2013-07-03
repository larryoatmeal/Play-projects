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
    Ok(views.html.inventory(ProductM.retrieveSome, sortForm, pageForm))
  }
  
  //DANGEROUS: Out of bounds are not really caught
  def inventorypage(page: Int) = Action {
    ProductM.currentPage = page
    Ok(views.html.inventory(ProductM.retrieveSome, sortForm, pageForm))

  }

  def inventoryAll = Action {

    Ok(views.html.inventory(ProductM.retrieveAll, sortForm, pageForm))
  }


  //Form takes Mapping[Object] as a paramater
  /*val productForm = Form(
  	mapping(
  		"name" -> text,
  		"price" -> number,
  		"id" -> number
  	)(ProductM.apply)(ProductM.unapply)
  )*/
  

  //Request data is put into a Map
  //Mapping is an object of type Mapping[T]
  //Takes a Map[String, String], and constructs an
  //object of type T

  //To process data in the form:
  //val processedForm = productForm.bind(data)
  //To check whether data has errors
  //

  //custom construction. Don't need ID input
  val productForm = Form(
  	mapping (
  		"name" -> nonEmptyText,
  		"price" -> number,
      "checked" -> checked("Please accept terms"),
      "img" -> text
  	)
  	((name, price, _, img) => ProductM(name, price,ProductM.randomID, img))
  	((product: ProductM) => Some(product.name, product.price, false, product.img))
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
  			//Ok("Created: " + value)
        Ok(views.html.inventory(ProductM.retrieveSome(), sortForm, pageForm))
  		}
  	)
  }
  def delete(id: Int)  = Action { implicit request =>
    ProductM.delete(id)
    Ok(views.html.inventory(ProductM.retrieveSome(), sortForm, pageForm))
  }


  //We want id in form to be the actual id of product
  val productFormUpdate = Form(
    mapping (
      "name" -> nonEmptyText,
      "price" -> number,
      "checked" -> checked("Please accept terms"),
      "img" -> text,
      "id" -> number
    )
    ((name, price, _, img, id) => ProductM(name, price, id, img))
    ((product: ProductM) => Some(product.name, product.price, false, product.img, product.id))
  )


  def update(id: Int) = Action {
    val product = ProductM.retrieveOne(id)
    val filledForm = productFormUpdate.fill(product)
    Ok(views.html.updateproduct(filledForm))
  }

  def modifyToList = Action { implicit request =>
    productFormUpdate.bindFromRequest.fold(
      //Fail, resend form, repopulate fields

      //Must use different html, which doesn't take in
      //another product. We can't get the product from here
      formWithErrors => Ok(views.html.updateproduct(formWithErrors)),
      //Success. Value is the object passed in
      value => {
        ProductM.update(value)
        //Ok("Created: " + value)
        Ok(views.html.inventory(ProductM.retrieveSome(), sortForm, pageForm))
      }
    )
  }

  val sortForm = Form(
    "sorttype" -> nonEmptyText
  ).fill("name")//name is default. Is overriden if needed
  //not necessary anymore when stored in model anyway
  def sort = Action { implicit request =>
    sortForm.bindFromRequest.fold(
      formWithErrors => Ok("ERROR"),
      value => {
        ProductM.currentSortType = value.toString
        Ok(views.html.inventory(
          ProductM.retrieveSome(),
          sortForm.fill(ProductM.currentSortType),
          pageForm
          )
        )
      }   
    )
  }


  val pageturn = Action { implicit request =>
    pageForm.bindFromRequest.fold(
      formWithErrors => Ok("ERROR"),
      value => {
        ProductM.currentPage = value
        Ok(views.html.inventory(
          ProductM.retrieveSome(),
          sortForm.fill(ProductM.currentSortType),
          pageForm.fill(ProductM.currentPage)
          )
        )
      }   
    )
  }

  val pageForm = Form(
      "page" -> number
  ).fill(1)







}