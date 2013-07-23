package models


import anorm._
import anorm.SqlParser._
import play.api.Play.current
import play.api.db.DB
import play.api.Logger



//Instrument contains an instrument name and multiple entries

case class Instrument(name: String, instrument_id: Option[Int])
case class Entry(user_id: Int, comment: Option[String], date: String, img: Option[String], entry_id: Option[Int], instrument_id: Int)
case class Display(instrument: Instrument, entries: List[Entry])
case class EntryJoinUser(entry: Entry, firstName: String, lastName: String)
case class Like(entry_id: Int, user_id: Int)


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
					row[Option[Int]]("instrument_id")
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
			ORDER BY entry_id
			""").on("instrument_id" -> instrument_id)().map(
				row => EntryJoinUser(
					Entry(
						row[Int]("entry.user_id"),
						row[Option[String]]("entry.comment"),
						row[String]("entry.date"),
						row[Option[String]]("entry.img"),
						row[Option[Int]]("entry.entry_id"),
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
			INSERT INTO entry (user_id, comment, date, img, instrument_id)
			VALUES({user_id}, {comment}, {date}, {img}, {instrument_id})
			""").on(
				"user_id" -> entry.user_id,
				"comment" -> entry.comment,
				"date" -> entry.date,
				"img" -> entry.img,
				"instrument_id" -> entry.instrument_id
			).executeUpdate() == 1
	}
	def newInstrument(instrument: Instrument) = DB.withConnection{
		implicit connection =>
		SQL("""
			INSERT INTO instrument(name)
			VALUES({name})
			"""
			).on(
				"name" -> instrument.name			
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
						row[Option[Int]]("instrument_id")
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
					row[Option[Int]]("entry_id"),
					row[Int]("instrument_id")
				)
			).toList
	}

	def newLike(like: Like) = DB.withConnection{
		implicit connection =>
		SQL("""
			INSERT INTO likes
			VALUES ({entry_id},{user_id})
			""").on(
			"entry_id" -> like.entry_id,
			"user_id" -> like.user_id
			).executeUpdate() == 1
	}
	def getLikes(entry_id: Int) = DB.withConnection{
		implicit connection =>
		SQL("""
			SELECT user_id
			FROM likes
			WHERE entry_id = {entry_id}
			"""
		).on("entry_id" -> entry_id)().map(
		 	row => row[Int]("likes.user_id")
		).toList
	}
	def removeLike(like: Like) = DB.withConnection{
		implicit connection =>
		SQL("""
			DELETE FROM
			likes
			WHERE entry_id = {entry_id} AND 
			user_id = {user_id}
			""").on(
			"entry_id" -> like.entry_id,
			"user_id" -> like.user_id
			).executeUpdate() == 1
	}




}
