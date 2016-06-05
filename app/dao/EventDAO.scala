package dao

import javax.inject.{Inject, Singleton}

import services.DbConnection
import models.{Event, EventInfo}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 * Created by tomek on 2/24/16.
 */
@Singleton
class EventDAO @Inject() (dbConnection: DbConnection) {
//  def eventInfo(ids:List[String]):Future[List[EventInfo]] = Future {
//
//  }
}
