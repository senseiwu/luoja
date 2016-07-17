package b2b.dao

import java.sql.Timestamp
import scala.concurrent.Future

import b2b.model.{Deal, DealDone, DealRej}
import com.google.inject.Inject
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.db.NamedDatabase
import slick.driver.JdbcProfile

/**
  * Created by tomek on 6/4/16.
  */

class DealDAO @Inject() (@NamedDatabase("b2b") val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] {
  import driver.api._
  private val deals = TableQuery[DealsTable]

  def insert(deal:Deal):Future[Long] = db.run((deals returning deals.map(_.id)) += deal)

  private class DealsTable(tag:Tag) extends Table[Deal](tag, "DEALS") {
    def id = column[Long]("ID", O.PrimaryKey, O.AutoInc)
    def parentId = column[Long]("PARENT_ID")
    def created = column[Timestamp]("CREATED")
    def updated = column[Timestamp]("UPDATED")
    def clientId = column[Long]("CLIENT_ID")
    def itemId = column[Long]("ITEM_ID")
    def orderId = column[Long]("ORDER_ID")
    def * = (id,parentId,created,updated,clientId,itemId,orderId) <> (Deal.tupled, Deal.unapply)
  }

  private class DealDoneTable(tag:Tag) extends Table[DealDone](tag, "DEALS_DONE") {
    def id = column[Long]("ID", O.PrimaryKey, O.AutoInc)
    def parentId = column[Long]("PARENT_ID")
    def created = column[Timestamp]("CREATED")
    def comment = column[String]("COMMENT")
    def * = (id,parentId,created,comment) <> (DealDone.tupled, DealDone.unapply)
  }

  private class DealRejTable(tag:Tag) extends Table[DealRej](tag, "DEALS_REJ") {
    def id = column[Long]("ID", O.PrimaryKey, O.AutoInc)
    def parentId = column[Long]("PARENT_ID")
    def created = column[Timestamp]("CREATED")
    def comment = column[String]("COMMENT")
    def * = (id,parentId,created,comment) <> (DealRej.tupled, DealRej.unapply)
  }
}
