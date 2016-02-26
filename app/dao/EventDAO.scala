package dao

import java.util.concurrent.Future
import javax.inject.{Inject, Singleton}

import db.DbConnection
import models.EventInfo
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by tomek on 2/24/16.
 */
@Singleton
class EventDAO @Inject() (dbConnection: DbConnection) {

  def eventsUserId(uid:String):Future[List[EventInfo]] = ???

  def suggestionsUserId(uid:String):Future[List[EventInfo]] = ???

  def suggestionsTags(tags:List[String]):Future[List[EventInfo]] = ???
  
}
