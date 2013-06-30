package controllers

import play.api._
import play.api.mvc._

object MainController2 extends Controller {
  
  // implicit reqeust => 

  def home = Action {
    Ok(views.html.home("Lilycove City"))
  }
  

}