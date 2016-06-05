package b2b.dao

import java.sql.Timestamp

import b2b.model.Item
import com.google.inject.Inject
import play.db.NamedDatabase
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile

/**
  * Created by tomek on 6/5/16.
  */
class ItemDAO @Inject() (@NamedDatabase("b2b") val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] {
  import driver.api._
  private val items = TableQuery[ItemsTable]

  private class ItemsTable(tag: Tag) extends Table[Item](tag, "ITEMS") {
    def id = column[Long]("ID", O.PrimaryKey, O.AutoInc)
    def sourceId = column[Long]("SOURCE_ID")
    def askPx = column[Double]("ASK_PX")
    def itemTag = column[String]("TAG")
    def itemType = column[Int]("TYPE")
    def created = column[Timestamp]("CREATED")
    def updated = column[Timestamp]("UPDATED")
    def isActive = column[Boolean]("IS_ACTIVE")
    def * = (id,sourceId,askPx,itemTag,itemType,created,updated,isActive) <> (Item.tupled, Item.unapply)
  }
}
