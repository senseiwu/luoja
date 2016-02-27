package dao

import javax.inject.{Inject, Singleton}

import com.mongodb.casbah.Imports._
import db.{DbConnection, Schema}
import models.{UserProfile, EventInfo, UserPP}

import scala.concurrent.{Future}

import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by tomek on 2/24/16.
 */
@Singleton
class UserDAO @Inject() (dbConnection: DbConnection) {

  def login(name:String, password:String):Future[UserPP] = Future {
    UserPP("id", name, "Tomasz", "Kozlowski", "Scala dev", "London",
      List("Scala", "Play", "Java"), List("Startups", "UX"))
  }

  def signin(name:String, password:String) = ???

  def updateProfile() = ???

  def getEvents(uid:ObjectId) = ???
}


object DB {
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
    UserPP("1234", "q@w", "Tomek", "Kozlowski", "Scala developer", "London",
    List("Scala", "Java", "Play", "Akka"),
    List("startup", "UX", "design")),
    List(e1), List(e1,e3), List(e4)
  )
}
