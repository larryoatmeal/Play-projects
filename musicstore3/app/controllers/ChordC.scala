package controllers

import play.api.mvc.{Action, Controller}
import models._
import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.Logger
import play.api.libs.Files


object ChordC extends Controller with Secured{

  //helper function
  def launch(code: => play.api.mvc.Result) = Action{
  	implicit request =>
  	code
  }
  def launchSecured(code: String => play.api.mvc.Result) = IsAuthenticated {
  	username => implicit request =>

  	code(username)
  }
  def getJson(code: JsValue => play.api.mvc.Result) = Action(parse.json) { 
  	request =>
  	code(request.body)


    // val entryJSON = request.body
    // val entry =  entryJSON.as[Entry]


    
    // try {
    //   InstrumentPageM.newEntry(entry)
    //   Ok("Success") //return some kind of message
    // } catch {
    //   case e:IllegalArgumentException =>
    //   BadRequest("Error, not added")
    // }
   
  }



  //Json
  implicit object SongWrites extends Writes[Song] {
  	def writes(s: Song) = Json.obj(
    	"content" -> Json.toJson(s.content),
    	"user_id" -> Json.toJson(s.user_id),
    	"composer" -> Json.toJson(s.composer),
    	"date" -> Json.toJson(s.date),
    	"song_id" -> Json.toJson(s.song_id),
      "title" -> Json.toJson(s.title)	
	) 
  }
  //entry={user_id: 1, comment: "Hey", date: "A long time ago", img: null, instrument_id: 1};

  //Json to object
  //Need to import play.api.libs.functional.syntax._
  implicit val songReads: Reads[Song] = (
    (JsPath \ "content").readNullable[String] and
    (JsPath \ "user_id").read[Int] and
    (JsPath \ "composer").readNullable[String] and
    (JsPath \ "date").readNullable[String] and
    (JsPath \ "song_id").readNullable[Int] and
    (JsPath \ "title").readNullable[String]
  )(Song.apply _)


  implicit val renderReads: Reads[RenderObject] = (
    (JsPath \ "content").read[String] and
    (JsPath \ "origKey").read[String] and
    (JsPath \ "destKey").read[String]
  )(RenderObject.apply _)




  def chordMain = launchSecured{
  	username => //function takes string value as paramater. Type inferred. Doesn't even need to be called username
  	val user = UserM.getUser(username)
  	Ok(views.html.chords(user))
  }
  def newSong(user_id: Int) = launchSecured{
  	username =>

  	ChordM.newSong(user_id)
  	Ok("Song created")
  }
  def saveSong = getJson{
  	json =>
  	//Ok(request)
  	val song = json.as[Song]
    //Logger.debug(song.toString)
  	ChordM.saveSong(song)
  	Ok("Saved song")
  }
  def getSong(song_id: Int) = launchSecured{
  	username =>
  	Ok(Json.toJson(ChordM.getSong(song_id)))
  }
  def deleteSong(song_id: Int) = launchSecured{
  	username =>
  	ChordM.deleteSong(song_id)
  	Ok("Deleted song")

  }
  def getSongs(user_id: Int) = launchSecured{
  	username =>
  	Ok(Json.toJson(ChordM.getSongs(UserM.getUserID(username))))
  }

  //Render doesn't work??? Same thing. Is render a reserved word?
  //Suddenly stopped working when I added an extra parentheses.
  //Use renderMusic instead
  @deprecated def render(raw: String) = Action{
    implicit request =>
    //Logger.debug(ChordM.formatSong(raw))
    //Logger.debug(raw)
    //println(raw)
    //println(raw)
    //Ok(ChordM.formatSong(raw))
    Ok(ChordM.formatSong(raw))
  }

  def renderWithOptions = getJson{
    json =>
    val renderObject = json.as[RenderObject]
    Logger.debug("Hello")
    Ok(ChordM.formatSong(renderObject.content, renderObject.origKey, renderObject.destKey))
  }


  def dummy = Action {
    implicit request =>
    Ok(request.toString)

  }


  def renderMusic(raw: String) = Action{
    implicit request =>
    Ok(ChordM.formatSong(raw))
  }

  def download(filename: String) = Action{
    implicit request =>
    Ok.sendFile(new java.io.File("text/"+filename))

  }

  def writefile(text: String, filename: String) = Action{
    implicit request =>
    Files.writeFile(new java.io.File(s"text/$filename"),text)
    Ok(text)
  }


  //def saveSong




}