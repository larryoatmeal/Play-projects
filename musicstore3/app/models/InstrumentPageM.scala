package models


import anorm._
import anorm.SqlParser._
import play.api.Play.current
import play.api.db.DB
import play.api.Logger



//Instrument contains an instrument name and multiple entries

case class Instrument(name: String, instrument_id: Int)
case class Entry(user_id: Int, comment: Option[String], date: String, img: Option[String], entry_id: Int, instrument_id: Int)
case class Display(instrument: Instrument, entries: List[Entry])
case class EntryJoinUser(entry: Entry, firstName: String, lastName: String)
object InstrumentPageM{

	def getDisplay(instrument_id: Int) = {
		Display(getInstrument(instrument_id),getListOfEntries(instrument_id))
	}
	def getListOfInstruments = DB.withConnection{
		implicit connection =>
		SQL("""
			SELECT * FROM instrument
			""")().map(
				row => Instrument(
					row[String]("name"),
					row[Int]("instrument_id")
				)
			).toList
	}
	def joinEntryWithUserList(instrument_id: Int) = DB.withConnection{
		implicit connection =>
		SQL("""
			SELECT *
			FROM entry
			JOIN user
			ON entry.user_id = user.user_id
			WHERE instrument_id = {instrument_id}
			""").on("instrument_id" -> instrument_id)().map(
				row => EntryJoinUser(
					Entry(
						row[Int]("entry.user_id"),
						row[Option[String]]("entry.comment"),
						row[String]("entry.date"),
						row[Option[String]]("entry.img"),
						row[Int]("entry.entry_id"),
						row[Int]("entry.instrument_id")
					),
					row[String]("user.firstName"),
					row[String]("user.lastName")
				)
			).toList

	}


	def newEntry(entry: Entry) = DB.withConnection{
		implicit connection =>
		SQL("""
			INSERT INTO entry
			VALUES({user_id}, {comment}, {date}, {img}, {entry_id}, {instrument_id})
			""").on(
				"user_id" -> entry.user_id,
				"comment" -> entry.comment,
				"date" -> entry.date,
				"img" -> entry.img,
				"entry_id" -> entry.entry_id,
				"instrument_id" -> entry.instrument_id
			).executeUpdate() == 1
	}
	def newInstrument(instrument: Instrument) = DB.withConnection{
		implicit connection =>
		SQL("""
			INSERST INTO instrument
			VALUES({name},{instrument_id})
			"""
			).on(
				"name" -> instrument.name,
				"instrument_id" -> instrument.instrument_id
			).executeUpdate() == 1
	}
	private def getInstrument(instrument_id: Int) = DB.withConnection{
		implicit connection =>
		SQL("""
			SELECT * FROM instrument
			WHERE instrument_id = {instrument_id}
			""").on("instrument_id" -> instrument_id)().map(
				row => Instrument(
						row[String]("name"),
						row[Int]("instrument_id")
					)
			).toList(0)
	}
	private def getListOfEntries(instrument_id: Int) = DB.withConnection{
		implicit connection =>
		SQL("""
			SELECT *
			FROM entry
			WHERE instrument_id = {instrument_id}
			""").on("instrument_id" -> instrument_id)().map(
				row => Entry(
					row[Int]("user_id"),
					row[Option[String]]("comment"),
					row[String]("date"),
					row[Option[String]]("img"),
					row[Int]("entry_id"),
					row[Int]("instrument_id")
				)
			).toList
	}





}

object Entry{





}

