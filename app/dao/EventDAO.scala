package dao

import javax.inject.{Inject, Singleton}

import db.DbConnection
import models.{UserPP, UserProfile, EventInfo}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 * Created by tomek on 2/24/16.
 */
@Singleton
class EventDAO @Inject() (dbConnection: DbConnection) {

  def eventsUserId(user:UserPP):Future[UserProfile] = Future {
    UserProfile(user,List(DB.e1, DB.e2),List(DB.e1, DB.e2),List(DB.e1, DB.e2))
  }

  def suggestionsUserId(uid:String):Future[List[EventInfo]] = ???

  def suggestionsTags(tags:List[String]):Future[List[EventInfo]] = ???

}
