package controllers

import play.api._
import play.api.mvc._
import models.PokeItemModel
import models.AnOrder
import play.api.data._
import play.api.data.Forms._

object PokeItemController extends Controller {
  
	def inventory = Action { implicit request =>
		//Send model data to View HTML
		val inventoryList = PokeItemModel.getInventory
		Ok(views.html.inventory(inventoryList))
	}


	def itemDetails(itemName: String) = Action { implicit request =>

		var isInInventory = false;
		//check if in inventory
		for (pokeItem <- PokeItemModel.getInventory){
			Logger.info(pokeItem.name)
			Logger.info(isInInventory.toString)
			if (pokeItem.name.toLowerCase == itemName.toLowerCase){
				isInInventory = true;
			}

		}
		if (isInInventory){Ok(views.html.itemdetails(itemName))}
		else {Ok(views.html.itemdetailserror(itemName))}
		
	}


	val orderForm= Form(
		mapping(
			"Select" -> text,
			"Quantity" -> number
		)(AnOrder.apply)(AnOrder.unapply)
	)

	val requestForm= Form(
		mapping(
			"price"->number,
			"name"->text,
			"description"->text,
			"id" -> number
		)(PokeItemModel.apply)(PokeItemModel.unapply)
	)

	def order = Action{ implicit request =>
		val inventoryList = PokeItemModel.getInventory
		Ok(views.html.orderitem(orderForm, inventoryList))
	}

	def receipt = Action{ implicit request =>
		val receiptForm = orderForm.bindFromRequest()
		val thisOrder = receiptForm.get
		val submittedItemName = thisOrder.Select
		val submittedQuantity = thisOrder.Quantity

		Logger.info(submittedItemName)
		receiptForm.fold(
			/*
			errors => Ok(views.html.itemdetailserror(submittedItemName)),
			Select => {
				val item = stringToItem(submittedItemName)
				Ok(views.html.receipt(item))
			}*/
		
			hasErrors = { orderForm =>
				Ok(views.html.itemdetailserror("OOOPS"))
			},
			success = { newOrder =>
				val item = stringToItem(submittedItemName)
				AnOrder.addToList(receiptForm.get)
				Logger(AnOrder.orderList.toString)
				Ok(views.html.receipt(item, submittedQuantity))

			}

		)
	}

	def checkout = Action{ implicit request =>
		Ok(views.html.checkout(AnOrder.getOrderList))



	}

	def request = Action{ implicit request =>
		

		Ok(views.html.request(requestForm))

	}

	def addItem = Action {implicit request =>
		val newRequestForm = requestForm.bindFromRequest()
		PokeItemModel.addToList(newRequestForm.get)
		Ok(views.html.home("Lilycove City"))


	}

	def stringToItem(stringName: String) = {
		var matchingItem = new PokeItemModel(0,"","",0)
		var isInInventory = false
		//check if in inventory
		for (pokeItem <- PokeItemModel.getInventory){
			
			if (pokeItem.name.toLowerCase == stringName.toLowerCase){
				isInInventory = true
				matchingItem = pokeItem
			}
		}

		
		matchingItem
		
		//unhanded exception
		//

	}


  

}