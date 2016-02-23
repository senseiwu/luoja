package db

import com.mongodb.casbah.Imports._

/**
 * Created by tomek on 2/5/16.
 */
class Mongo(cl:MongoClient, dbname:Option[String] = None) extends DbBase {
  override val client:MongoClient = cl
  override val dbName: Option[String] = dbname
}

object Mongo {
  def apply():Mongo = new Mongo(MongoClient())
  def apply(host:String):Mongo = new Mongo(MongoClient(host))
  def apply(host:String, port:Int):Mongo = new Mongo(MongoClient(host, port))
  def apply(client:MongoClient):Mongo = new Mongo(client)
  def apply(client:MongoClient, dbName:String):Mongo = new Mongo(client, Some(dbName))
}