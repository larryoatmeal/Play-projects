package controllers

import play.api._
import play.api.mvc._
import models._
import play.api.data._
import play.api.data.Forms._
import scala.util.Random
object PurchaseC extends Controller {
  
  def purchase(productid: Int) = Action {

    Ok(views.html.purchaseproduct(purchaseForm, productid))
  }

  def receipt = Action {
   
    Ok(views.html.receipt(PurchaseM.joinProductPurchase))

  }

  val purchaseForm = Form(
    mapping (
      "productid" -> number,
      "firstName" -> nonEmptyText,
      "lastName" -> nonEmptyText,
      "quantity" -> number,
      "email" -> optional(text),
      "customerid" -> number.verifying( input => !PurchaseM.duplicates(input) )
    )//must be in order!
    (PurchaseM.apply)
    (PurchaseM.unapply)
  )

  def addToList(productid: Int) = Action { implicit request =>

    purchaseForm.bindFromRequest.fold(

      formWithErrors =>{
        Logger.debug("Errror")
        Ok(views.html.purchaseproduct(formWithErrors, productid))

        } ,
      value => {
      PurchaseM.add(value)
      ProductC.inventoryRedirect
    }
    )
  }



}