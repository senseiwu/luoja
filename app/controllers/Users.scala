package controllers

import javax.inject.Inject

import dao.{DB, EventDAO, UserDAO}
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc.{BodyParsers, Action, Controller}
import play.api.Logger
import play.api.libs.json._

import scala.concurrent.Future


import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by tomek on 2/10/16.
 */
class Users @Inject()(val messagesApi: MessagesApi, userDao:UserDAO, eventDao:EventDAO) extends Controller with I18nSupport {

  import models._

  def signin = Action.async(BodyParsers.parse.json) {
    implicit request =>
      request.body.validate[UserLogin].fold(
        error => Future.successful(BadRequest("")),
        user =>
          userDao.login(user.name, user.password).map((u:AuthStatus) => {
            Ok(Json.toJson(u))
          }).recoverWith{
            case e: Exception => Future {
              Forbidden
            }
          }
      )
  }

  def signin2 = Action.async(BodyParsers.parse.json) {
    implicit request =>
      request.body.validate[UserLogin].fold(
        error => Future.successful(BadRequest("")),
        user =>
          userDao.findByEmail(user.name).map((u:User) => {
            Ok(Json.toJson(AuthStatus(AuthStatus.AUTH_SUCCESS, Some(u))))
          }).recoverWith{
            case e: Exception => Future {
              Logger.info("ERR: " + e)
              Forbidden
            }
          }
      )
  }

  def dashboard(login:String) = Action.async {
    userDao.events(login).map((ud:UserDashboard) => {
      Ok(Json.toJson(ud))
    })
  }

  def suggestions(userName:String) = Action.async {
    Logger.info("Suggestions | " + userName)
    userDao.suggestions(userName).map((s:List[EventInfo]) =>
      Ok(Json.toJson(s))
    )
  }

  def register = Action {
    Ok("Not implemented yet")
  }

  def details(username:String) = Action {
    Ok("Not implemented yet")
  }
}
