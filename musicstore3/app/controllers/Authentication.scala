package controllers
import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models._


object Authentication extends Controller {

	val loginForm = Form{
		//tuple is a method of Forms: Forms.tuple returns a Mapping, which is part of Play
		tuple(
			"email" -> text,
			"password" ->  text
		).verifying( "DENIED",
			//verifying is a method of Mapping[T], returns another Mapping[T]
			formResult => UserM.authenticate(formResult._1, formResult._2)
				//will return true or false
		)
	}//binding taken care of


	val signupForm = Form(
		mapping(
			"user_id" -> number,
			"firstName" -> nonEmptyText,
			"lastName" -> nonEmptyText,
			"creditCard" -> number,
			"email" -> nonEmptyText,
			"password" -> nonEmptyText,
			"address" -> optional(text)
		)
		(UserM.apply)(UserM.unapply)
	)


	val dummyLoginGood = loginForm.fill(Tuple2("Larry", "password"))
	val dummyLoginBad = loginForm.fill(Tuple2("Larry", "noidea"))

	def login = Action { implicit request =>
		Ok(views.html.login(loginForm))
	}

	def submit = Action { implicit request =>
		loginForm.bindFromRequest.fold(
			formWithErrors => {
				Logger.debug("Couldn't log in")
				Ok(views.html.login(formWithErrors))},
			value => {
				Logger.debug("Logged in")
				Redirect(routes.Application.index).withSession("email" ->
				value._1)
				}//email is first value of form tuple
		)
	}

	def submitNewUser = Action { implicit request =>
		signupForm.bindFromRequest.fold(
			formWithErrors => {
				Ok(views.html.signup(formWithErrors))},
			value => {
				UserM.addUser(value)
				Redirect(routes.Authentication.login)
				}//email is first value of form tuple
		)
	}


	def logout = Action {implicit request => 
		Redirect(routes.Authentication.login).withNewSession
	}

	def signup = Action {
		Ok(views.html.signup(signupForm))
	}

}





trait Secured {

	//Get connected user's email
	private def username(request: RequestHeader) = {
		Logger.debug(request.toString)
		request.session.get("email")


	}

	//Redirect to login if unauthorized

	private def onUnauthorized(request: RequestHeader) = 

		Results.Redirect(routes.Authentication.login)

	def IsAuthenticated(f: => String => Request[AnyContent] => Result) = 
	//f is a function that if of type I don't even know
	//IsAuthenticated takes in a function f that tells you
	//what to do if authentication passes 

	//Security.Authenticated(userInfo: RequestHeader => Option[A],
	//onUnauthorized: RequestHeader => SimpleResult)
	//Third paramter is the entire function block
		Security.Authenticated(username, onUnauthorized){
			user => Action(request => f(user)(request))

		//So if f is the function passed in, the function you pass in
		//can take those variables
		//so, if we define
		/*
			def index = IsAuthenticated { username => _ => 
			doStuff}

			then username will be fillled with the user passed in here

		*/
		//This block is actually a paramater of Security.Authenticated
		//Got it
		//Okay, so the 

	}


}