package controllers

import models._
import play.api._
import play.api.mvc._

object GuitarC extends Controller with Secured {
  
  def guitar = IsAuthenticated { username => implicit request =>
    Ok(views.html.guitar())
    //username is actually the field passed in from Security.Authenticate
  }

}

