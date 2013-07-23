package controllers

import play.api._
import play.api.mvc._
import models._
import play.api.data._
import play.api.data.Forms._
import scala.util.Random
object ProductC extends Controller with Secured{
  
 /* val tempList = List(
  	ProductM("Piano", 100, 0),
  	ProductM("Trombone", 500, 1),
  	ProductM("Xylophone", 300, 2)
  	)*/


  //*************  GETS  *******************//
  def inventory(page: Int = 1, sort: String = "name", filter: String = "%", 
    searchForm: Form[Tuple2[String,Option[String]]] = filterForm ) = Action {
  	//Logger.info(tempList.toString)
    //Get list of products from database    
    Ok(views.html.inventory(ProductM.retrieve(page, sort, filter), page, sort, filter, searchForm))
    //searchForm is really filterForm
  }

  
  //Redirect to inventory, fill with default URL query. Routes back to inventory method in this class
  val inventoryRedirect = Redirect(routes.ProductC.inventory(1, "name", "%"))//% wild card

  def inventoryAll = Action {
    Ok(views.html.inventory(ProductM.retrieveAll, 1, "name", "%", filterForm))
  }
  def sell = Action {
    //Logger.info(ProductM.lastID.toString)
    Ok(views.html.sellproduct(productForm))

  }
   def update(id: Int) = Action {
    val product = ProductM.retrieveOne(id)
    val filledForm = productFormUpdate.fill(product)
    Ok(views.html.updateproduct(filledForm))
  }

  

  //***********  FORM CONSTANTS   *************//
  val productForm = Form(
  	mapping (
  		"name" -> nonEmptyText,
  		"price" -> number,
      "checked" -> checked("Please accept terms"),
      "img" -> text
  	)
  	//((name, price, _, img) => ProductM(name, price,ProductM.randomID, img))
    //Don't need to actually pass in ID because SQL will generate it anyway
    //Insert in the model doesn't actually read the ID value of the product passed
    //in through the form
    ((name, price, _, img) => ProductM(name, price, -1, img))
  	((product: ProductM) => Some(product.name, product.price, false, product.img))
  )

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

  val ViableSearchModes = List("id", "name", "price")
  val filterForm = Form(
    mapping(
      "search" -> nonEmptyText,
      "constraint" -> optional(text.verifying(input => ViableSearchModes.contains(input.toLowerCase)))
    )
    //Apply
    ((search, constraint) => new Tuple2(search, constraint))
    //Unapply
    ((result: Tuple2[String, Option[String]]) => Some(result._1, result._2))
  )





//*****************FORM PROCESSSING ***************//
  def addToList = Action { implicit request =>
  	productForm.bindFromRequest.fold(
  		//Fail, resend form, repopulate fields
  		formWithErrors => Ok(views.html.sellproduct(formWithErrors)),
  		//Succes
  		value => {
  			ProductM.insert(value)
  			//Ok("Created: " + value)
        inventoryRedirect
  		}
  	)
  }
  def delete(id: Int)  = Action { implicit request =>
    ProductM.delete(id)
    inventoryRedirect
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
        inventoryRedirect
      }
    )
  }
  //Form won't accept regulary method
  def inventoryHelper(page: Int = 1, sort: String = "name", filter: String = "%", 
    searchForm: Form[Tuple2[String,Option[String]]] = filterForm ) = Ok(views.html.inventory(ProductM.retrieve(page, sort, filter), page, sort, filter, searchForm))

  def filter = Action { implicit request =>
    filterForm.bindFromRequest.fold(
      formWithErrors =>inventoryHelper(searchForm = formWithErrors),
      value => {
        val filterString = value._2 match {
          case Some(constraint) => value._1 + ProductM.specialModeKey + constraint
          case None => value._1 //Don't add extra constraint
        } 

        Redirect(routes.ProductC.inventory(filter = filterString))
      }
    )
  }
  




  /* NOT NECESSARY: Use links instead!
  def sort = Action { implicit request =>
    sortForm.bindFromRequest.fold(
      formWithErrors => Ok("ERROR"),
      value => {
        ProductM.currentSortType = value.toString
        Ok(views.html.inventory(
          ProductM.retrieve(),
          sortForm,
          pageForm
          )
        )
      }   
    )
  }
  def pageturn = Action { implicit request =>
    pageForm.bindFromRequest.fold(
      formWithErrors => Ok("ERROR"),
      value => {
        ProductM.currentPage = value
        Ok(views.html.inventory(
          ProductM.retrieve(),
          sortForm,
          pageForm
          )
        )
      }   
    )
  }
    val sortForm = Form(
    "sorttype" -> nonEmptyText
  ).fill("name")//name is default. Is overriden if needed
  //not necessary anymore when stored in model anyway

  val pageForm = Form(
    "page" -> number
  ).fill(1)

//DANGEROUS: Out of bounds are not caught
  def inventorypage(page: Int) = Action {
    ProductM.currentPage = page
    Ok(views.html.inventory(ProductM.retrieve))
  }

  */

  























  //XXXXXXXXXXXXXXXXXX  DEPRECATED XXXXXXXXXXXXXXXXX//
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


}