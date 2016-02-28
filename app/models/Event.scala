package models

import javax.inject.{Inject, Singleton}

import db.{Schema, DbConnection}
import org.joda.time.LocalDate
import play.api.Logger

/**
 * Created by tomek on 2/2/16.
 */
// TODO: extend this class
case class Event(
                  _id:Long,
                  name:String,
                  description:String,
                  when:LocalDate,
                  where:String
                  )

object Event {
  import play.api.libs.json.Json
  implicit val eventFormat = Json.format[Event]
}