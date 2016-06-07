package b2b.dao

import java.sql.Timestamp
import java.text.SimpleDateFormat

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration.DurationInt
import b2b.model.Order
import org.specs2.mutable._
import play.api.{Application, Configuration, Mode}
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.test.WithApplicationLoader

/**
  * Created by tomek on 6/7/16.
  */

class OrderDAOTest extends Specification {

  val sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss")
  def timestamp(date:String) = new Timestamp(sdf.parse(date).getTime())

  val application = new GuiceApplicationBuilder()
    .configure("slick.dbs.b2b.driver"-> "slick.driver.H2Driver$")
    .configure("slick.dbs.b2b.db.driver" -> "org.h2.Driver")
    .configure("slick.dbs.b2b.db.url" -> "jdbc:h2:mem:test")
    .configure("slick.dbs.b2b.db.user" -> "sa")
    .configure("slick.dbs.b2b.db.password" -> "sa")
    .in(Mode.Test)
    .build

  "OrderInsert" should {
    "work as expected" in new WithApplicationLoader {
      val app2dao = Application.instanceCache[OrderDAO]
      val dao:OrderDAO = app2dao(application)
      val now = new Timestamp(System.currentTimeMillis())
      val testOrders = Set(
        Order(1,12345,22,"music",12.99,timestamp("11-08-2016 11:22:00"),timestamp("11-08-2016 11:22:00"),false)
      )
      Await.result(Future.sequence(testOrders.map(dao.insert)), 1 seconds)
      val ord = Await.result(dao.findByClientId(12345), 1 seconds)
      ord.toSet must equalTo(testOrders)
    }
  }

}
