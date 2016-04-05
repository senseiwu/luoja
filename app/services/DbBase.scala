package services
//
//import com.mongodb.casbah.Imports._
//import play.api.Logger

/**
 * Created by tomek on 2/5/16.
 */
trait DbBase {

//  val client:MongoClient
//  val dbName:Option[String]
//
//  def close = {
//    Logger.info("Closing DB - " + dbName.get)
//    client.close
//  }
//
//  def getdb():MongoDB = if(dbName.isDefined) getdb(dbName.get) else throw new RuntimeException("Unknown database name")
//
//  def getdb(name:String):MongoDB = client(name)
//
//  def collection(collection:String):MongoCollection =
//    if(dbName.isDefined) client(dbName.get)(collection) else throw new RuntimeException("Unknown database name")
//
//  def collection(dbname:String, collection:String):MongoCollection = client(dbname)(collection)
//
//  def findForKey(collectionObject:MongoCollection, key:String, value:String): MongoCursor =
//    collectionObject.find(key $eq value)
//
//  def findForType(collectionName:String, typeName:String): MongoCursor =
//    findForType(collection(collectionName), typeName)
//
//  def findEventByCategory(collection: MongoCollection, category:String):MongoCursor =
//    collection.find(Schema.EventCategory $eq category)
//
//  def findForType(collectionObject:MongoCollection, typeName:String): MongoCursor =
//    findForKey(collectionObject, "type", typeName)

}
