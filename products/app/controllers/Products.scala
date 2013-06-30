package controllers

import play.api.mvc.{Action, Controller}
import models.Product
import play.api.data.Form
import play.api.data.Form.{mapping, longNumber, nonEmptyText}
import play.api.il8n.

object Products extends Controller{

	def list = Action{ implicit request =>
		val products = Product.findAll

		Ok(views.html.products.list(products))
	}

	def show(ean: Long) = Action { implicit request =>

		Product.findByEan(ean).map{ product =>
			Ok(views.html.products.details(product))
		}.getOrElse(Ok(views.html.products.error()))
	}
ß



}