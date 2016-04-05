package services

//import com.mongodb.casbah.Imports._
import org.joda.time.LocalDate

/**
 * Created by tomek on 2/5/16.
 */

object Schema {
//  def eventMongoObj(name:String, description:String, organizer:ObjectId, when:LocalDate,
//                    lectures:List[MongoDBObject], category:ObjectId, tags:List[String],
//                    venue:MongoDBObject, subscribedUsers:List[ObjectId]):MongoDBObject =
//    MongoDBObject(
//      "name" -> name,
//      "description" -> description,
//      "organizer" -> organizer,
//      "when" -> when,
//      "lectures" -> lectures,
//      "venue" -> venue,
//      "category" -> category,
//      "tags" -> tags,
//      "subscribed_users" -> subscribedUsers)
//
//  def lectureMongoObj(title:String, description:String, speaker:ObjectId, length:Int, tags:List[String]):MongoDBObject =
//    MongoDBObject(
//      "title" -> title,
//      "description" -> description,
//      "speaker" -> speaker,
//      "length" -> length,
//      "tags" -> tags
//    )
//
//  def userMongoObj(username:String, email:String, first_name:String, last_name:String, hashed_passwd:String,
//                   job_position:String, location:String, suggestedEvents:List[ObjectId], upcomingEvents:List[ObjectId],
//                   pastEvents:List[ObjectId], tags:List[String], skills:List[String]):MongoDBObject =
//    MongoDBObject(
//      "name" -> username,
//      "email" -> email,
//      "first_name" -> first_name,
//      "second_name" -> last_name,
//      "hashed_passwd" -> hashed_passwd,
//      "job_position" -> job_position,
//      "location" -> location,
//      SuggestedUserEvents -> suggestedEvents,
//      UpcomingUserEvents -> upcomingEvents,
//      PastUserEvents -> pastEvents,
//      "tags" -> tags,
//      "skills" -> skills
//    )
//
//  def venueMongoObj(name:String, addrObj:MongoDBObject, floor:Int, room:String):MongoDBObject = {
//    MongoDBObject(
//      "name" -> name,
//      "address" -> addrObj,
//      "floor" -> floor,
//      "room" -> room
//    )
//  }
//
//  def addressMongoObj(street:String, city:String, state_province:String, country:String, zip:String):MongoDBObject = {
//    MongoDBObject(
//      "street" -> street,
//      "city" -> street,
//      "state_province" -> state_province,
//      "country" -> country,
//      "zip" -> zip
//    )
//  }

  val EventCollection:String = "event"
  val UserCollection:String = "users"

  val Name: String = "name"
  val Description: String = "description"
  val EventWhen: String = "when"
  val EventCategory: String = "category"

  val SuggestedUserEvents:String = "suggested_events"
  val UpcomingUserEvents:String = "upcoming_events"
  val PastUserEvents:String = "past_events"

}
