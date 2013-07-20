package controllers

import play.api.mvc.{Action, Controller}
import models._
import play.api.libs.json._

object InstrumentPage extends Controller{

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
    	"lastName" -> Json.toJson(e.lastName)	
	) 
  }




 def mainPage = Action {
 	Ok(views.html.instrument())

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

