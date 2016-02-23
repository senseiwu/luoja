package controllers

import javax.inject.Inject

import com.mongodb.casbah.commons.MongoDBObject
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
class Users @Inject()(val messagesApi: MessagesApi, dbConn:DbConnection) extends Controller with I18nSupport {

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
          if(user.name.equals("q@w") && user.password.equals("qw")){
            Ok(Json.obj(
              "status" -> 0,
              "user" -> Json.toJson(up))
            )
          }
          else{
            Ok(Json.obj("status" -> 1, "message" -> "User signin failure"))
          }
        }
      )
  }

  def dashboard(login:String) = Action {
    Ok(Json.obj(
      "status" -> 0,
      "user" -> Json.toJson(up))
    )
  }

  def register = Action {
    Ok("Not implemented yet")
  }

  def details(username:String) = Action {
    Ok("Not implemented yet")
  }

  val e1 = EventInfo(
    "1234",
    "Scala reactive programming",
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut at ultricies nisl, sed ornare nunc. Maecenas elit nisi, tincidunt ac dui eu, commodo interdum odio.",
    "Nov 30th, 2016", 12, 19)

  val e2 = EventInfo(
    "1234",
    "SZJUG - Java User Group Shenzhen",
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut at ultricies nisl, sed ornare nunc. Maecenas elit nisi, tincidunt ac dui eu, commodo interdum odio.",
    "May 14th, 2016", 33, 112)

  val e3 = EventInfo(
    "1234",
    "Microservices in action",
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut at ultricies nisl, sed ornare nunc. Maecenas elit nisi, tincidunt ac dui eu, commodo interdum odio.",
    "Nov 12th, 2016", 33, 2)

  val e4 = EventInfo(
    "1234",
    "UX design",
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut at ultricies nisl, sed ornare nunc. Maecenas elit nisi, tincidunt ac dui eu, commodo interdum odio.",
    "Nov 12th, 2016", 33, 2)

  val up = UserProfile(
    "Tomek", "Kozlowski", "q@w", "Scala developer", "London",
    List("Scala", "Java", "Play", "Akka"),
    List("startup", "UX", "design"),
    List(e1), List(e3), List(e4)
  )

}
