package services

import javax.inject.{Inject, Singleton}

import org.mongodb.scala.{MongoClient, MongoDatabase}
import play.api.Configuration
import play.api.Logger
import play.api.inject.ApplicationLifecycle

import scala.concurrent.Future

/**
 * Created by tomek on 2/10/16.
 */
@Singleton
class DbConnection @Inject() (lifecycle: ApplicationLifecycle, configuration: Configuration) {
  val dbName: String = configuration.getString("mongo.db.name").get
  val client: MongoClient = MongoClient()
  val db: MongoDatabase = {
    Logger.info("Connecting to mongo " + dbName)
    client.getDatabase(dbName)
  }

  lifecycle.addStopHook {
    () => {
      Logger.info("Closing mongo connection")
      Future.successful(client.close())
    }
  }


}
