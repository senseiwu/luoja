package b2b.dao

import java.sql.Timestamp
import java.text.SimpleDateFormat

import b2b.model.{Item}
import org.specs2.mutable._
import play.api.{Application, Mode}
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.test.WithApplication

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration.DurationInt

/**
  * Created by tomek on 7/7/16.
  */
class ItemDAOTest extends Specification {
  val sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss")
  def timestamp(date:String) = new Timestamp(sdf.parse(date).getTime())
  def maskId(items:Set[Item]) = items.map(o => o.copy(0))

  val application = new GuiceApplicationBuilder()
    .configure("slick.dbs.b2b.driver"-> "slick.driver.H2Driver$")
    .configure("slick.dbs.b2b.db.driver" -> "org.h2.Driver")
    .configure("slick.dbs.b2b.db.url" -> "jdbc:h2:mem:b2b")
    .configure("slick.dbs.b2b.db.user" -> "sa")
    .configure("slick.dbs.b2b.db.password" -> "sa")
    .in(Mode.Test)
    .build

  val app2dao = Application.instanceCache[ItemDAO]
  val dao:ItemDAO = app2dao(application)

  "ItemInsert" should {
    "worak as expected" in new WithApplication {
      val tag = System.currentTimeMillis().toString
      val items = Set(
        Item(0, 12, 12.4, tag, 2, timestamp("11-08-2016 11:22:00"), timestamp("11-08-2016 11:22:00"), true)
      )
      Await.result(Future.sequence(items.map(dao.insert)), 1 seconds)
      val res = Await.result(dao.list(Some(13.0), tag, None), 1 seconds)
      maskId(res.toSet) must equalTo(items)
    }
  }

  "Find items for tags, skip price" should {
    "list out only security" in new WithApplication() {
      val tag1 = System.currentTimeMillis().toString
      val tag2 = (System.currentTimeMillis()+1000).toString
      val tag3 = (System.currentTimeMillis()+2000).toString
      val items = Set(
        Item(0, 11, 12.4, tag1, 2, timestamp("11-08-2016 11:22:00"), timestamp("11-08-2016 11:22:00"), true),
        Item(0, 12, 12.4, tag2, 2, timestamp("11-08-2016 11:22:00"), timestamp("11-08-2016 11:22:00"), true),
        Item(0, 13, 12.4, tag2, 2, timestamp("11-08-2016 11:22:00"), timestamp("11-08-2016 11:22:00"), true),
        Item(0, 14, 12.4, tag3, 2, timestamp("11-08-2016 11:22:00"), timestamp("11-08-2016 11:22:00"), true)
      )
      Await.result(Future.sequence(items.map(dao.insert)), 1 seconds)
      val res = Await.result(dao.list(None, tag2, None), 1 seconds)
      val expected = Set(Item(0, 12, 12.4, tag2, 2, timestamp("11-08-2016 11:22:00"), timestamp("11-08-2016 11:22:00"), true),
        Item(0, 13, 12.4, tag2, 2, timestamp("11-08-2016 11:22:00"), timestamp("11-08-2016 11:22:00"), true))
      maskId(res.toSet) must equalTo(expected)
    }
  }

  "Find items for tags like-pattern, skip price" should {
    "list out only security" in new WithApplication() {
      val items = Set(
        Item(0, 11, 12.4, "tag music", 2, timestamp("11-08-2016 11:22:00"), timestamp("11-08-2016 11:22:00"), true),
        Item(0, 12, 12.4, "tag music and lighting", 2, timestamp("11-08-2016 11:22:00"), timestamp("11-08-2016 11:22:00"), true),
        Item(0, 13, 12.4, "tag security", 2, timestamp("11-08-2016 11:22:00"), timestamp("11-08-2016 11:22:00"), true),
        Item(0, 14, 12.4, "beer", 2, timestamp("11-08-2016 11:22:00"), timestamp("11-08-2016 11:22:00"), true)
      )
      Await.result(Future.sequence(items.map(dao.insert)), 1 seconds)
      val res = Await.result(dao.list(None, "%music%", None), 1 seconds)
      val expected = Set(
        Item(0, 11, 12.4, "tag music", 2, timestamp("11-08-2016 11:22:00"), timestamp("11-08-2016 11:22:00"), true),
        Item(0, 12, 12.4, "tag music and lighting", 2, timestamp("11-08-2016 11:22:00"), timestamp("11-08-2016 11:22:00"), true))
      maskId(res.toSet) must equalTo(expected)
    }
  }

  "Find items for sourceId" should {
    "list out only right one" in new WithApplication() {
      val tag1 = System.currentTimeMillis().toString
      val tag2 = (System.currentTimeMillis()+1001).toString
      val tag3 = (System.currentTimeMillis()+2001).toString
      val items = Set(
        Item(0, 111, 12.4, tag1, 2, timestamp("11-08-2016 11:22:00"), timestamp("11-08-2016 11:22:00"), true),
        Item(0, 112, 12.4, tag2, 2, timestamp("11-08-2016 11:22:00"), timestamp("11-08-2016 11:22:00"), true),
        Item(0, 113, 12.4, tag2, 2, timestamp("11-08-2016 11:22:00"), timestamp("11-08-2016 11:22:00"), true),
        Item(0, 112, 12.4, tag3, 2, timestamp("11-08-2016 11:22:00"), timestamp("11-08-2016 11:22:00"), true)
      )
      Await.result(Future.sequence(items.map(dao.insert)), 1 seconds)
      val res = Await.result(dao.findForSourceId(112), 1 seconds)
      val expected = Set(
        Item(0, 112, 12.4, tag2, 2, timestamp("11-08-2016 11:22:00"), timestamp("11-08-2016 11:22:00"), true),
        Item(0, 112, 12.4, tag3, 2, timestamp("11-08-2016 11:22:00"), timestamp("11-08-2016 11:22:00"), true))
      maskId(res.toSet) must equalTo(expected)
    }
  }

  "Find items for tags,price" should {
    "list out only active records" in new WithApplication() {
      val items = Set(
        Item(0, 11, 12.4, "tag svc1", 2, timestamp("11-08-2016 11:22:00"), timestamp("11-08-2016 11:22:00"), false),
        Item(0, 12, 12.4, "tag svc1 and svc3", 2, timestamp("11-08-2016 11:22:00"), timestamp("11-08-2016 11:22:00"), true),
        Item(0, 13, 12.4, "tag svc4", 2, timestamp("11-08-2016 11:22:00"), timestamp("11-08-2016 11:22:00"), true),
        Item(0, 14, 12.4, "svc1", 2, timestamp("11-08-2016 11:22:00"), timestamp("11-08-2016 11:22:00"), true)
      )
      Await.result(Future.sequence(items.map(dao.insert)), 1 seconds)
      val res = Await.result(dao.list(None, "%svc1%", None), 1 seconds)
      val expected = Set(
        Item(0, 12, 12.4, "tag svc1 and svc3", 2, timestamp("11-08-2016 11:22:00"), timestamp("11-08-2016 11:22:00"), true),
        Item(0, 14, 12.4, "svc1", 2, timestamp("11-08-2016 11:22:00"), timestamp("11-08-2016 11:22:00"), true))
      maskId(res.toSet) must equalTo(expected)
    }
  }

}
