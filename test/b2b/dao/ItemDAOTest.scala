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
    .configure("slick.dbs.b2b.db.url" -> "jdbc:h2:mem:test")
    .configure("slick.dbs.b2b.db.user" -> "sa")
    .configure("slick.dbs.b2b.db.password" -> "sa")
    .in(Mode.Test)
    .build

  val app2dao = Application.instanceCache[ItemDAO]
  val dao:ItemDAO = app2dao(application)

  /*
  * "OrderInsert" should {
    "work as expected" in new WithApplication {
      val testOrders = Set(
        Order(0,12345,22,"music",12.99,timestamp("11-08-2016 11:22:00"),timestamp("11-08-2016 11:22:00"),false),
        Order(0,12345,11,"Light",12.99,timestamp("11-08-2016 11:22:00"),timestamp("11-08-2016 11:22:00"),false)
      )
      Await.result(Future.sequence(testOrders.map(dao.insert)), 1 seconds)
      val ord = Await.result(dao.findByClientId(12345), 1 seconds)
      maskId(ord.toSet) must equalTo(testOrders)
    }
  }*/

  "ItemInsert" should {
    "worak as expected" in new WithApplication {
      val items = Set(
        Item(0, 12, 12.4, "music", 2, timestamp("11-08-2016 11:22:00"), timestamp("11-08-2016 11:22:00"), true)
      )
      Await.result(Future.sequence(items.map(dao.insert)), 1 seconds)
      val item = Await.result(dao.list(), 1 seconds)
      item.foreach(println(_))
      1 must beEqualTo(1)
      //val itemsR = Await.result(dao.list(), 1 seconds)
      //println("items: " + itemsR.toSet)
    }
  }
}
