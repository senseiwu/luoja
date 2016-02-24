package dao

import javax.inject.{Inject, Singleton}

import com.mongodb.casbah.Imports._
import db.{DbConnection, Schema}
import models.User

/**
 * Created by tomek on 2/24/16.
 */
@Singleton
class UserDAO @Inject() (dbConnection: DbConnection, eventDAO: EventDAO) {

  // TODO: db connect
  //val collection = dbConnection.db.collection(Schema.UserCollection)

  def login(name:String, password:String):User = {
    // TODO: db connect
    //    val cr = collection.findOne(Schema.Name $eq name)
//    if (cr.isDefined) mongoObjToUser(cr.get)
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
