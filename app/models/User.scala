package models

/**
 * Created by tomek on 2/7/16.
 */

case class UserLL(id:String, status:Int, tags:List[String])
case class UserPP(id:String, email:String, firstName:String, secondName:String, jobPosition:String, location:String,
                 skills:List[String], interests:List[String]
                 )
case class UserLogin(name:String, password:String)
case class UserProfile(user:UserPP, suggestedEventsInfo:List[EventInfo], upcomingEventsInfo:List[EventInfo],
                       pastEventsInfo:List[EventInfo])



