package controllers

import play.api._
import play.api.mvc._
import play.api.data.Form
import play.api.data.Forms.{mapping, longNumber, nonEmptyText}
import models.Pokemon
object Application extends Controller {
  
  def index = Action {
    Ok(views.html.index("User"))
  }

  def about = Action{
  	Ok(views.html.about("Alpha"))
  }

  def pokemon = Action{
  	Ok(views.html.pokemon(new Pokemon(25L, "Pikachu", "Electric")))

  }

  def pokename(number: Int) = Action{ implicit request =>


  	Ok(views.html.pokename(number))
  }

  //Form
  private val pokemonForm: Form[Pokemon] = Form(
  	mapping(
  		"number" -> longNumber.verifying(
  			"validation.number.duplicate", Pokemon.findByNumber(_).isEmpty),
  		"name" -> nonEmptyText,
  		"description" -> nonEmptyText

  		)(Pokemon.apply)(Pokemon.unapply)
  	)



  
}