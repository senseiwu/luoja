package controllers

import javax.inject.Inject

import com.mongodb.casbah.commons.MongoDBObject
import dao.{DB, EventDAO, UserDAO}
import db.{Schema, DbConnection}
import models.UserLogin
import play.api.data.Form
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc.{BodyParser, BodyParsers, Action, Controller}
import play.api.Logger
import play.api.libs.json._

import scala.concurrent.Future
import scala.util.{Success,Failure}

import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by tomek on 2/10/16.
 */
class Users @Inject()(val messagesApi: MessagesApi, userDao:UserDAO, eventDao:EventDAO) extends Controller with I18nSupport {

  import models._
  import models.JsonFormats._

  def signin = Action.async(BodyParsers.parse.json) {
    implicit request =>
      request.body.validate[UserLogin].fold(
        error => Future.successful(BadRequest("")),
        user =>
          userDao.login(user.name, user.password).map((u:UserPP) => {
            if(u.id != "") {
              Logger.info("User logged in: " + u.id)
              Ok(Json.obj("status" -> 0, "user" -> Json.toJson(u)
              ))
            } else {
              Logger.info("LOGIN FAILURE: " + user.name)
              Ok(Json.obj("status" -> 1, "message" -> "User signin failure"))
            }
          }).recoverWith{
            case e: Exception => Future {
              Forbidden
            }
          }
      )
  }

  def dashboard(login:String) = Action {
    Logger.info("User: " + login + " logged in and dashboard generated for him")
    Ok("NaN")
  }

  def register = Action {
    Ok("Not implemented yet")
  }

  def details(username:String) = Action {
    Ok("Not implemented yet")
  }
}
