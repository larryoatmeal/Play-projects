package controllers

import play.api._
import play.api.mvc._
import model.Task
import play.api.data._
import play.api.data.Forms._

object Application extends Controller {
  
  def index = Action {
    //Ok(views.html.index("Your new application is ready."))
    //Ok("Hello world,\nMy name is Larry")
    Redirect(routes.Application.tasks)
  }
  
  def tasks = Action{
  	val myTask = new Task(23, "Larry")
  	Ok(views.html.index(myTask))
  }

  def newTask = TODO

  def deleteTask(id: Long) = TODO

  val taskForm = Form(
  	"label" -> nonEmptyText

  	)

}