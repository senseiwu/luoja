package controllers

import javax.inject.Inject

import com.mongodb.casbah.commons.MongoDBObject
import dao.{EventDAO, UserDAO}
import db.{Schema, DbConnection}
import models.UserLogin
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc.{BodyParsers, Action, Controller}
import play.api.Logger
import play.api.libs.json._

import scala.concurrent.Future

/**
 * Created by tomek on 2/10/16.
 */
class Users @Inject()(val messagesApi: MessagesApi, userDao:UserDAO, eventDao:EventDAO) extends Controller with I18nSupport {

  import models._
  import models.JsonFormats._

  def signin = Action(BodyParsers.parse.json) {
    request =>
      val ulogin = request.body.validate[UserLogin]
      ulogin.fold(
        errors => {
          BadRequest(Json.obj("status" -> 405, "message" -> JsError.toJson(errors)))
        },
        user => {
          Ok("")
//          if(true){
//            Ok(Json.obj(
//              "status" -> 0,
//              "user" -> Json.toJson(up))
//            )
//          }
//          else{
//            Ok(Json.obj("status" -> 1, "message" -> "User signin failure"))
//          }
        }
      )
  }

  def dashboard(login:String) = Action {
//    Ok(Json.obj(
//      "status" -> 0,
//      "user" -> Json.toJson(up))
//    )
    Ok("NaN")
  }

  def register = Action {
    Ok("Not implemented yet")
  }

  def details(username:String) = Action {
    Ok("Not implemented yet")
  }



}
