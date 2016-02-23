package models

import javax.inject.{Inject, Singleton}

import db.{Schema, DbConnection}
import org.joda.time.LocalDate
import play.api.Logger

/**
 * Created by tomek on 2/2/16.
 */
case class Event(id:Long, name:String, description:String, when:LocalDate, where:String)
case class Topic(id:Long, name:String, tags:String)

case class EventInfo(profileId:String, name:String, info:String, when:String, peopleCount:Int, commentsCount:Int)

@Singleton
class EventDAO @Inject() (dbConnection: DbConnection) {
  // TODO: db connect
  //val collection = dbConnection.db.collection(Schema.EventCollection)

  var events = Set(
    Event(1, "Devoxx", "bla bla bal", LocalDate.parse("2015-7-19"), "Geneva"),
    Event(2, "JDD", "jsdfjhsdf", LocalDate.parse("2016-2-3"), "Hong Kong"),
    Event(3, "UX", "bla bla bal", LocalDate.parse("2016-2-3"), "Shenzhen"),
    Event(4, "ScalaDays", "jsdfjhsdf", LocalDate.parse("2016-5-7"), "Tokyo"),
    Event(5, "SZJUG", "bla bla bal", LocalDate.parse("2016-5-13"), "Zurich"),
    Event(6, "Python", "jsdfjhsdf", LocalDate.parse("2015-4-20"), "Krakow"),
    Event(7, "Python2", "jsdfjhsdf", LocalDate.parse("2015-12-2"), "Warsaw"),
    Event(8, "Python3", "jsdfjhsdf", LocalDate.parse("2016-4-20"), "Krakow"),
    Event(9, "Python4", "jsdfjhsdf", LocalDate.parse("2016-12-20"), "Krakow")
  )

  def add(event:Event) = {
    events = events + event
    Logger.info("Events: " + events.size + " -- " + events)
  }

  def get(id:Long) = events.find(_.id == id)

  def finAll = events.toList.sortBy(_.id)

  def getAll = List()// TODO: db connect collection.find

  def findAllToday = events.toList.filter(ev => ev.when.isEqual(LocalDate.now))

  def findAllPast = events.toList.filter(ev => ev.when.isBefore(LocalDate.now))

  def findAllUpcoming = events.toList.filter(ev => ev.when.isAfter(LocalDate.now))

}
