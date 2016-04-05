package controllers

import javax.inject.Inject

import dao.EventDAO
import services.{DbConnection, Mongo}
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

  def details(eventName:String) = Action {
    Ok("NA")
  }

  def upcoming = Action {
//    implicit request =>
//      Ok(views.html.events.list(eventDAO.findAllUpcoming))
    Ok("NA")
  }

  def newEvent = Action {
    Ok("Not implemented yet")
  }

  def save = Action {
    Ok("Not implemented yet")
  }

}
