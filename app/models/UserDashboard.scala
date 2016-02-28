package models

/**
 * Created by tomek on 2/28/16.
 */
case class UserDashboard(
                          upcomingEventsInfo:List[EventInfo],
                          pastEventsInfo:List[EventInfo]
                          )

object UserDashboard {
  import play.api.libs.json.Json
  implicit val userDashboardFormt = Json.format[UserDashboard]
}
