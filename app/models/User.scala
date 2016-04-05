package models

import org.bson.types.ObjectId

/**
 * Created by tomek on 2/7/16.
 */

case class User(
                 id:String,
                 email:String,
                 firstName:String,
                 secondName:String,
                 jobPosition:String,
                 location:String,
                 skills:List[String],
                 interests:List[String],
                 pastEvents:List[String],
                 upcomingEvents:List[String]
                 )

object User {
  import play.api.libs.json.Json
  implicit val userFormat = Json.format[User]
}
