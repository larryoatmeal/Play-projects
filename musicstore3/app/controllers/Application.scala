package controllers

import models._
import play.api._
import play.api.mvc._

object Application extends Controller with Secured {
  
  def index = IsAuthenticated { username => implicit request =>
    Ok(views.html.home(UserM.getUser(username)))
    //username is actually the field passed in from Security.Authenticate
  }
  def dummy(message: String) = Action {
    Ok(views.html.todo(message))
  }
  
}