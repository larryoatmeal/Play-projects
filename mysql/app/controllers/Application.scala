package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {
  
  def home = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def about = TODO

  





  
}