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



