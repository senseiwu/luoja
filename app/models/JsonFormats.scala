package models

/**
 * Created by tomek on 2/24/16.
 */
object JsonFormats {
  import play.api.libs.json.Json
  implicit val userll = Json.format[UserLL]
  implicit val userLogin = Json.format[UserLogin]
  implicit val eventInfo = Json.format[EventInfo]
  implicit val userpp = Json.format[UserPP]
  implicit val userProfile = Json.format[UserProfile]
}
