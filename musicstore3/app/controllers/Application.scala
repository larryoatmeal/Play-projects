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
  

  def javascriptRoutes = Action { implicit request =>
    import routes.javascript._
    Ok(
      Routes.javascriptRouter("jsRoutes")(
      	Authentication.logout,
      	InstrumentPage.addEntry,
        InstrumentPage.addInstrument,
        InstrumentPage.like,
        InstrumentPage.listOfLikes,
        InstrumentPage.unlike,
        InstrumentPage.likersName,
        ChordC.getSong,
        ChordC.newSong,
        ChordC.saveSong,
        ChordC.deleteSong,
        ChordC.getSongs,
        ChordC.render,
        ChordC.renderMusic,
        ChordC.renderWithOptions,
        ChordC.dummy,
        ChordC.writefile,
        ChordC.download,
        ChordC.musicXML,
        ChordC.downXML,
        ChordC.chordMidi
      )
    ).as("text/javascript") 
  }


}