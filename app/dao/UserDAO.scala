package dao

import javax.inject.{Inject, Singleton}

import com.mongodb.casbah.Imports._
import db.{DbConnection, Schema}
import models.{AuthStatus, UserDashboard, EventInfo, User}
import org.joda.time.format.{DateTimeFormatter, DateTimeFormatterBuilder, DateTimeFormat}
import org.joda.time.{DateTime, LocalDate}

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
      ids._2.map(DB.evColl(_))
    )
  }

  def suggestions(userName:String):Future[List[EventInfo]] = Future {
    DB.suggColl(userName).map(DB.evColl(_))
  }

  def signin(name:String, password:String) = ???

  def updateProfile() = ???

}


object DB {

  val date1 = new DateTime(2016, 3, 10, 12, 0, 0, 0)
  val date2 = new DateTime(2016, 3, 16, 12, 0, 0, 0)
  val date3 = new DateTime(2016, 3, 19, 12, 0, 0, 0)
  val date4 = new DateTime(2016, 3, 2, 12, 0, 0, 0)

  val fmt:DateTimeFormatter = new DateTimeFormatterBuilder()
            .appendDayOfMonth(2)
            .appendLiteral('-')
            .appendMonthOfYearShortText()
            .appendLiteral('-')
            .appendTwoDigitYear(1956)  // pivot = 1956
            .toFormatter()

  val e1 = EventInfo(
    "1111",
    "Scala reactive programming",
    "scalareact",
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut at ultricies nisl, sed ornare nunc. Maecenas elit nisi, tincidunt ac dui eu, commodo interdum odio.",
    Some(date1.toString(fmt)), 12, 19)

  val e2 = EventInfo(
    "1112",
    "SZJUG - Java User Group Shenzhen",
    "szjugmar19",
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut at ultricies nisl, sed ornare nunc. Maecenas elit nisi, tincidunt ac dui eu, commodo interdum odio.",
    Some(date2.toString(fmt)), 33, 112)

  val e3 = EventInfo(
    "1113",
    "Microservices in action",
    "microsvcs",
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut at ultricies nisl, sed ornare nunc. Maecenas elit nisi, tincidunt ac dui eu, commodo interdum odio.",
    Some(date3.toString(fmt)), 33, 2)

  val e4 = EventInfo(
    "1114",
    "UX design",
    "uxdesign",
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut at ultricies nisl, sed ornare nunc. Maecenas elit nisi, tincidunt ac dui eu, commodo interdum odio.",
    None, 33, 2)

  val up = User("1234", "q@w", "Tomek", "Kozlowski", "Scala developer", "London",
    List("Scala", "Java", "Play", "Akka"),
    List("startup", "UX", "design"))

  val ud = (List("1111","1114"), List("1112"))

  val suggColl = Map("q@w" -> List("1111", "1114"))
  val evColl = Map("1111" -> e1, "1112" -> e2, "1113" -> e3, "1114" -> e4)
  val usrColl = Map("q@w" -> ("ppp", up, ud))

}
