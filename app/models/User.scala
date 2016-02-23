package models

import javax.inject.{Inject, Singleton}

import db.{Schema, DbConnection}
import com.mongodb.casbah.Imports._

import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._

/**
 * Created by tomek on 2/7/16.
 */

case class User(id:String, name:String, events:List[String], tags:List[String])
case class UserLogin(name:String, password:String)
case class UserProfile(firstName:String,secondName:String, email:String, jobPosition:String,
                       location:String, skills:List[String], interests:List[String],
                       suggestedEventsInfo:List[EventInfo], upcomingEventsInfo:List[EventInfo], pastEventsInfo:List[EventInfo])

object JsonFormats {
  import play.api.libs.json.Json
  implicit val userLogin = Json.format[UserLogin]
  implicit val eventInfo = Json.format[EventInfo]
  implicit val userProfile = Json.format[UserProfile]
}

@Singleton
class UserDAO @Inject() (dbConnection: DbConnection, eventDAO: EventDAO) {

  val collection = dbConnection.db.collection(Schema.UserCollection)

  def login(name:String, password:String):User = {
    val cr = collection.findOne(Schema.Name $eq name)
    if (cr.isDefined) mongoObjToUser(cr.get)
    User(null,null,null,null)
  }

  def mongoObjToUser(mongoDBObject: MongoDBObject):User = {
    User(
      mongoDBObject.get("_id").asInstanceOf,
      mongoDBObject.get("name").asInstanceOf,
      mongoDBObject.get(Schema.SuggestedUserEvents).toList.asInstanceOf,
      mongoDBObject.get("tags").toList.asInstanceOf
    )
  }

  def signin(name:String, password:String) = ???

  def updateProfile() = ???

  def getEvents(uid:ObjectId) = ???
}
