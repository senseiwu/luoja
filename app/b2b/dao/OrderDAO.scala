package b2b.dao

import java.sql.Timestamp

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import b2b.model.Order
import com.google.inject.Inject
import play.api.Logger
import play.db.NamedDatabase
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile

/**
  * Created by tomek on 6/4/16.
  */
class OrderDAO @Inject() (@NamedDatabase("b2b") val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] {
  import driver.api._
  private val orders = TableQuery[OrdersTable]

//  val q = for { c <- coffees if c.name === "Espresso" } yield c.price
//  val updateAction = q.update(10.49)

  def insert(order:Order): Future[Unit] = db.run(orders += order).map{_=> ()}
  def updatePx(id:Long, px:Double) = {
    val q = for {
      ord <- orders if ord.id === id
    } yield ord.bidPx
    db.run(q.update(px))
  }
  def update(order:Order) = {
    val o = orderById(order.id)
  }
  def updateType(id:Long, itemType:Int) = ???
  def updateTag(id:Long, itemTag:String) = ???
  def updateDealFlag(id:Long, hasDeal:Boolean) = ???

  def findByClientId(cid:Long):Future[Seq[Order]] = db.run(orders.filter(_.clientId === cid).result)
  def findByTag(itag:String):Future[Seq[Order]] = db.run(orders.filter(_.itemTag === itag).result)

  private def orderById(id:Long) = orders.filter(_.id === id).result

  private class OrdersTable(tag: Tag) extends Table[Order](tag, "ORDERS") {
    def id = column[Long]("ID", O.PrimaryKey, O.AutoInc)
    def clientId = column[Long]("CLIENT_ID")
    def itemType = column[Int]("ITEM_TYPE")
    def itemTag = column[String]("ITEM_TAG")
    def bidPx = column[Double]("BID_PX")
    def created = column[Timestamp]("CREATED")
    def updated = column[Timestamp]("UPDATED")
    def hasDeal = column[Boolean]("HAS_DEAL")
    def * = (id, clientId, itemType, itemTag, bidPx,created,updated,hasDeal) <> (Order.tupled, Order.unapply)
  }
}
