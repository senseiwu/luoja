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

  def insert(order:Order): Future[Long] = db.run((orders returning orders.map(_.id)) += order)

  def updatePx(id:Long, px:Double) = {
    val q = for {
      ord <- orders if ord.id === id
    } yield ord.bidPx
    db.run(q.update(px))
  }
  def update(order:Order) = db.run(orderById(order.id).update(order))
  def updateType(id:Long, itemType:Int) = db.run(orderById(id).map(_.itemType).update(itemType))
  def updateTag(id:Long, itemTag:String) = db.run(orderById(id).map(_.itemTag).update(itemTag))
  def updateDealFlag(id:Long, hasDeal:Boolean) = db.run(orderById(id).map(_.hasDeal).update(hasDeal))

  def findById(id:Long):Future[Option[Order]] = db.run(orderById(id).result.headOption)
  def findByClientId(cid:Long):Future[Seq[Order]] = db.run(orders.filter(_.clientId === cid).result)
  def findByTag(itag:String):Future[Seq[Order]] = db.run(orders.filter(_.itemTag === itag).result)

  private def orderById(id:Long) = orders.filter(_.id === id)

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
