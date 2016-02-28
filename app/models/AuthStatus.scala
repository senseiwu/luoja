package models


/**
 * Created by tomek on 2/28/16.
 */
case class AuthStatus(
                       status:Int,
                       user:Option[User]
                       )

object AuthStatus {
  import play.api.libs.json.Json
  implicit val authStatusFormat = Json.format[AuthStatus]

  val AUTH_SUCCESS = 0
  val AUTH_FAILURE = -1
}
