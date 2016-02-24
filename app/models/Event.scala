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


