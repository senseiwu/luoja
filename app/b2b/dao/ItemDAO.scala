package b2b.dao

import java.sql.Timestamp

import b2b.model.Item
import com.google.inject.Inject
import play.db.NamedDatabase
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by tomek on 6/5/16.
  */
class ItemDAO @Inject() (@NamedDatabase("b2b") val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] {
  import driver.api._
  private val items = TableQuery[ItemsTable]

  def insert(item:Item):Future[Long] = db.run((items returning items.map(_.id)) += item)

  def list(px:Option[Int] = None, itag:String = "%", itype:Option[Int] = None):Future[Seq[Item]] = {
    val query = items.filter { item =>
      List(
        Option(itag).map(item.itemTag like _),
        itype.map(item.itemType === _)
      ).collect({case Some(criteria) => criteria}).reduceLeftOption(_ && _).getOrElse(true: Rep[Boolean])
    }
    println(query.result.statements.head)
    db.run(query.result)
  }

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
