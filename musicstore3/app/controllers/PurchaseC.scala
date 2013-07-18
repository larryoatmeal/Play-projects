package controllers

import play.api._
import play.api.mvc._
import models._
import play.api.data._
import play.api.data.Forms._
import scala.util.Random
object PurchaseC extends Controller with Secured{
  
  def purchase(productid: Int) = IsAuthenticated { username => implicit request =>

    Ok(views.html.purchaseproduct(purchaseForm, productid, UserM.getUser(username)))
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

  def addToList(productid: Int) = IsAuthenticated { username => implicit request =>

    purchaseForm.bindFromRequest.fold(

      formWithErrors =>{
        Logger.debug("Errror")
        Ok(views.html.purchaseproduct(formWithErrors, productid, UserM.getUser(username)))

        } ,
      value => {
      PurchaseM.add(value)
      ProductC.inventoryRedirect
    }
    )
  }



}