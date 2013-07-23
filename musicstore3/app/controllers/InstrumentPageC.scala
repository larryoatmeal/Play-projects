package controllers

import play.api.mvc.{Action, Controller}
import models._
import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.Logger

object InstrumentPage extends Controller with Secured{

  //Must come first in code
  //Lets you take Json.toJson(Entry)
  implicit object EntryWrites extends Writes[Entry] {
  	def writes(entry: Entry) = Json.obj(
    	"user_id" -> Json.toJson(entry.user_id),
    	"comment" -> Json.toJson(entry.comment),
    	"date" -> Json.toJson(entry.date),
    	"img" -> Json.toJson(entry.img)	
    	) 
  }
  implicit object InstrumentWrites extends Writes[Instrument] {
  	def writes(instrument: Instrument) = Json.obj(
    	"name" -> Json.toJson(instrument.name),
    	"instrument_id" -> Json.toJson(instrument.instrument_id)
	) 
  }
  implicit object EntryJoinUserWrites extends Writes[EntryJoinUser] {
  	def writes(e: EntryJoinUser) = Json.obj(
    	"user_id" -> Json.toJson(e.entry.user_id),
    	"comment" -> Json.toJson(e.entry.comment),
    	"date" -> Json.toJson(e.entry.date),
    	"img" -> Json.toJson(e.entry.img),
    	"firstName" -> Json.toJson(e.firstName),
    	"lastName" -> Json.toJson(e.lastName),
      "entry_id" -> Json.toJson(e.entry.entry_id)	
	) 
  }
  //entry={user_id: 1, comment: "Hey", date: "A long time ago", img: null, instrument_id: 1};

  //Json to object
  //Need to import play.api.libs.functional.syntax._
  implicit val entryReads: Reads[Entry] = (
    (JsPath \ "user_id").read[Int] and
    (JsPath \ "comment").readNullable[String] and
    (JsPath \ "date").read[String] and
    (JsPath \ "img").readNullable[String] and
    (JsPath \ "entry_id").readNullable[Int] and
    (JsPath \ "instrument_id").read[Int]
  )(Entry.apply _) 

 def mainPage = IsAuthenticated{ username => implicit request =>
  //Logger.debug("Hello")
 	Ok(views.html.instrument(UserM.getUserID(username)))

 }

 def instrumentList = Action {
    Ok(Json.toJson(InstrumentPageM.getListOfInstruments))
  }

  def details(instrument_id: Int) = Action {
  	/*val entryList = InstrumentPageM.getDisplay(instrument_id).entries
  	entryList.map(
  		entry => Json.toJson(entry)
  	).toSeq*/
  	//Json.toJson can take List[T] if T is accounted for with an
  	//implicit object
  	//Ok(Json.toJson(InstrumentPageM.getDisplay(instrument_id).entries))
  	Ok(Json.toJson(InstrumentPageM.joinEntryWithUserList(instrument_id)))

  }

  /*def addEntry(user_id: Int, instrument_id: Int, comment: Option[String], img: Option[String], date: String) = Action {
    play.api.Logger.debug("Added entry")
    val entry = Entry(user_id, comment, date, img, None, instrument_id)//database will create own entry ID
    InstrumentPageM.newEntry(entry)
    Ok(views.html.todo("Add Entry"))

  }*/

  //ajax sends over json, this guy parses it and adds it to the database
  def addEntry = Action(parse.json) { request =>
    val entryJSON = request.body
    val entry =  entryJSON.as[Entry]


    
    try {
      InstrumentPageM.newEntry(entry)
      Ok("Success") //return some kind of message
    } catch {
      case e:IllegalArgumentException =>
      BadRequest("Error, not added")
    }
   
  }

  def addInstrument(name: String) = Action { implicit request =>

    InstrumentPageM.newInstrument(Instrument(name, None))
    Logger.debug("added")
    Ok("success")
  }

  def like(entry_id: Int, user_id: Int) = Action {implicit request =>
    InstrumentPageM.newLike(Like(entry_id, user_id))
    Ok("Liked")
  }
  def unlike(entry_id: Int, user_id: Int) = Action { implicit request =>
    InstrumentPageM.removeLike(Like(entry_id, user_id))
    Ok("Unliked")

  }


  def listOfLikes(entry_id: Int) = Action { implicit request =>
    //getLikes returns list of integers, which Json can parse automatically
    //Logger.debug(Json.toJson(InstrumentPageM.getLikes(entry_id)).toString)
    Ok(Json.toJson(InstrumentPageM.getLikes(entry_id)))
  }

  def likersName(user_id: Int) = Action { implicit request =>
    Ok(UserM.getFirstName(user_id))
  }




  /*def javascriptRoutes = Action { implicit request =>
    import routes.javascript._
    Ok(
      Routes.javascriptRouter("jsRoutes")(
        Users.list,
        Users.get
      )
    ).as("text/javascript")
  }*/

/*
  val jsonObject = Json.toJson(
  Map(
    "users" -> Seq(
      toJson(
        Map(
          "name" -> toJson("Bob"),
          "age" -> toJson(31),
          "email" -> toJson("bob@gmail.com")
        )
      ),
      toJson(
        Map(
          "name" -> toJson("Kiki"),
          "age" -> toJson(25),
          "email" -> JsNull
        )
      )
    )
  )
)
*/

  
  

  //implicit objects allow us to modify other people's libraries
  //in this case we are modifying Json.toJson to include a Writes[Product] mtehod
  




}

