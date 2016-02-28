package models

/**
 * Created by tomek on 2/28/16.
 */
case class UserLogin(
                      name:String,
                      password:String
                      )

object UserLogin {
  import play.api.libs.json.Json
  implicit val userLoginFormt = Json.format[UserLogin]
}
