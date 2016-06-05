package b2b.dao

import java.sql.Timestamp
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

import b2b.model.Order
import com.google.inject.Inject
import play.db.NamedDatabase
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile

/**
  * Created by tomek on 6/4/16.
  */
class OrderDAO @Inject() (@NamedDatabase("b2b") val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] {
  import driver.api._
  private val orders = TableQuery[OrdersTable]

  def insert(order:Order): Future[Unit] = db.run(orders += order).map{_=> ()}
  def updatePx(px:Double) = ???
  def update(order:Order) = ???
  def updateType(itemType:Int) = ???
  def updateTag(itemTag:String) = ???
  def updateDealFlag(hasDeal:Boolean) = ???

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
