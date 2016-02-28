package dao

import javax.inject.{Inject, Singleton}

import db.DbConnection
import models.{Event, EventInfo}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 * Created by tomek on 2/24/16.
 */
@Singleton
class EventDAO @Inject() (dbConnection: DbConnection) {
  def eventInfoByIdSync(id:String):EventInfo = DB.evColl(id)
  def eventInfoByIdAsync(id:String):Future[EventInfo] = Future(eventInfoByIdSync(id))
  def eventByIdSync(id:String):Event = ???
  def eventByIdAsync(id:String):Future[Event] = Future(eventByIdSync(id))
}
