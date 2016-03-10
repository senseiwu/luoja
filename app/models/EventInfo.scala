package models

/**
 * Created by tomek on 2/28/16.
 */
case class EventInfo(
                      profileId:String,
                      name:String,
                      slug:String,
                      info:String,
                      when:String,
                      peopleCount:Int,
                      commentsCount:Int
                      )

object EventInfo {
  import play.api.libs.json.Json
  implicit val eventInfoFormat = Json.format[EventInfo]
}