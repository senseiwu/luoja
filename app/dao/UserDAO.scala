package dao

import javax.inject.{Inject, Singleton}

import com.mongodb.casbah.Imports._
import db.{DbConnection, Schema}
import models.{AuthStatus, UserDashboard, EventInfo, User}

import scala.concurrent.{Future}

import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by tomek on 2/24/16.
 */
@Singleton
class UserDAO @Inject() (dbConnection: DbConnection) {

  def login(name:String, password:String):Future[AuthStatus] = Future {
    //TODO: rework it to more scala way!
    val u = DB.usrColl(name)
    if (u._1.equals(password))
      AuthStatus(AuthStatus.AUTH_SUCCESS, Some(u._2))
    else
    AuthStatus(AuthStatus.AUTH_FAILURE, None)
  }

  def events(name:String):Future[UserDashboard] = Future {
    val ids = DB.usrColl(name)._3
    UserDashboard(
      ids._1.map(DB.evColl(_)),
      ids._2.map(DB.evColl(_)),
      ids._3.map(DB.evColl(_))
    )
  }

  def signin(name:String, password:String) = ???

  def updateProfile() = ???

}


object DB {

  val e1 = EventInfo(
    "1111",
    "Scala reactive programming",
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut at ultricies nisl, sed ornare nunc. Maecenas elit nisi, tincidunt ac dui eu, commodo interdum odio.",
    "Nov 30th, 2016", 12, 19)

  val e2 = EventInfo(
    "1112",
    "SZJUG - Java User Group Shenzhen",
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut at ultricies nisl, sed ornare nunc. Maecenas elit nisi, tincidunt ac dui eu, commodo interdum odio.",
    "May 14th, 2016", 33, 112)

  val e3 = EventInfo(
    "1113",
    "Microservices in action",
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut at ultricies nisl, sed ornare nunc. Maecenas elit nisi, tincidunt ac dui eu, commodo interdum odio.",
    "Nov 12th, 2016", 33, 2)

  val e4 = EventInfo(
    "1114",
    "UX design",
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut at ultricies nisl, sed ornare nunc. Maecenas elit nisi, tincidunt ac dui eu, commodo interdum odio.",
    "Nov 12th, 2016", 33, 2)

  val up = User("1234", "q@w", "Tomek", "Kozlowski", "Scala developer", "London",
    List("Scala", "Java", "Play", "Akka"),
    List("startup", "UX", "design"))

  val ud = (List("1111", "1114"), List("1111","1112"), List("1114"))

  val evColl = Map("1111" -> e1, "1112" -> e2, "1113" -> e3, "1114" -> e4)
  val usrColl = Map("q@w" -> ("ppp", up, ud))

}
