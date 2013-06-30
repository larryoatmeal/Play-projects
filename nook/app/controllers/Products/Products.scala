package controllers

import play.api._
import play.api.mvc._

object Products extends Controller {
  

  def home = Action {
    Ok(views.html.home("Lilycove City"))
  }

  def list(pagenumber: Int) = Action { //Show product list
  	NotImplemented
  }

  def details(id: Int) = Action { //See product details
  	NotImplemented
  }

  def edit(id: Int) = Action{ //Edit product details
  	NotImplemented
  }

  def update(id: Int) = Action{ //Update product details
  	NotImplemented
  }




  

}