package b2b.dao

import java.sql.Timestamp
import java.text.SimpleDateFormat

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration.DurationInt
import b2b.model.Order
import org.specs2.mutable._
import play.api.{Application, Logger, Mode}
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.test.{WithApplication}

/**
  * Created by tomek on 6/7/16.
  */

class OrderDAOTest extends Specification {

  val sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss")
  def timestamp(date:String) = new Timestamp(sdf.parse(date).getTime())

  val application = new GuiceApplicationBuilder()
    .configure("slick.dbs.b2b.driver"-> "slick.driver.H2Driver$")
    .configure("slick.dbs.b2b.db.driver" -> "org.h2.Driver")
    .configure("slick.dbs.b2b.db.url" -> "jdbc:h2:mem:b2b")
    .configure("slick.dbs.b2b.db.user" -> "sa")
    .configure("slick.dbs.b2b.db.password" -> "sa")
    .in(Mode.Test)
    .build

  val app2dao = Application.instanceCache[OrderDAO]
  val dao:OrderDAO = app2dao(application)

  def maskId(orders:Set[Order]) = orders.map(o => o.copy(0))

  //def insertOrder(order:Order):Long = Await.result(dao.insert(order), 1 seconds)

  "OrderInsert" should {
    "work as expected" in new WithApplication {
      val testOrders = Set(
        Order(0,12345,22,"music",12.99,timestamp("11-08-2016 11:22:00"),timestamp("11-08-2016 11:22:00"),false),
        Order(0,12345,11,"Light",12.99,timestamp("11-08-2016 11:22:00"),timestamp("11-08-2016 11:22:00"),false)
      )
      Await.result(Future.sequence(testOrders.map(dao.insert)), 1 seconds)
      val ord = Await.result(dao.findByClientId(12345), 1 seconds)
      maskId(ord.toSet) must equalTo(testOrders)
    }
  }

  "Order update" should {

    "complete order" in new WithApplication {
      val tor = Order(0,12341,22,"music",12.99,timestamp("11-08-2016 11:22:00"),timestamp("11-08-2016 11:22:00"),false)
      val id = Await.result(dao.insert(tor), 1 seconds)
      val onew = Order(id,12341,33,"sound",12.99,timestamp("11-08-2016 11:22:00"),timestamp("11-08-2016 11:22:00"),true)
      dao.update(onew)
      val ord = Await.result(dao.findById(id), 1 seconds)
      ord.toSet must equalTo(Set(onew))
    }

    "itemType changed correctly" in new WithApplication {
      val tor = Order(0,12341,22,"music",12.99,timestamp("11-08-2016 11:22:00"),timestamp("11-08-2016 11:22:00"),false)
      val id = Await.result(dao.insert(tor), 1 seconds)
      dao.updateType(id, 99)
      val ord = Await.result(dao.findById(id), 1 seconds)
      ord mustNotEqual None
      ord.get.itemType must beEqualTo(99)
    }

    "itempType not changed coz order id not exists" in new WithApplication {
      def findId(id:Long):Long = {
        val o = Await.result(dao.findById(id), 1 seconds)
        if (o.isDefined) findId(id+1)
        else id
      }
      val id = findId(1)
      Logger.info("Free ID: " + id)
      dao.updateType(id, 99)
//      val ord = Await.result(dao.findById(id), 1 seconds)
//      ord mustNotEqual None
//      ord.get.itemType must beEqualTo(99)
    }

  }

}
