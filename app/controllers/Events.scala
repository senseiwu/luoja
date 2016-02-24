package controllers

import javax.inject.Inject

import com.mongodb.casbah.MongoClient
import dao.EventDAO
import db.{DbConnection, Mongo}
import org.joda.time.LocalDate
import play.api.Play._
import play.api.http.Writeable
import play.api.mvc.{Flash, Action, Controller}
import models.Event
import play.api.i18n.{Messages, MessagesApi, I18nSupport}
import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.Flash
import play.api.Logger

/**
 * Created by tomek on 2/2/16.
 */
class Events @Inject()(val messagesApi: MessagesApi, eventDAO: EventDAO) extends Controller with I18nSupport {

  def list = Action {
    //implicit request => Ok(views.html.events.list(eventDAO.finAll))
    Ok("NA")
  }

  def upcoming = Action {
//    implicit request =>
//      Ok(views.html.events.list(eventDAO.findAllUpcoming))
    Ok("NA")
  }

  implicit object EventWrites extends Writes[Event] {
    override def writes(e: Event): JsValue = Json.obj(
      "id" -> Json.toJson(e.id),
      "name" -> Json.toJson(e.name),
      "description" -> Json.toJson(e.description),
      "when" -> Json.toJson(e.when.toString),
      "where" -> Json.toJson(e.where)
    )
  }

//  implicit object UserWrites extends Writes[User]

  def todayJson = Action {
    Ok(Json.toJson(eventDAO.findAllToday))
  }

  def pastJson = Action {
    Ok(Json.toJson(eventDAO.findAllPast))
  }

  def upcomingJson = Action {
    Ok(Json.toJson(eventDAO.findAllUpcoming))
  }

  def allJson = Action {
    Ok(eventDAO.getAll.mkString)
  }

  def newEvent = Action {
    Ok("Not implemented yet")
  }

  def save = Action {
    Ok("Not implemented yet")
  }

}
