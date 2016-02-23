package db

import javax.inject.{Inject, Singleton}

import com.mongodb.casbah.Imports._
import play.api.Logger
import play.api.inject.ApplicationLifecycle

import scala.concurrent.Future

/**
 * Created by tomek on 2/10/16.
 */
@Singleton
class DbConnection @Inject() (lifecycle:ApplicationLifecycle) {
  // TODO: db connect
  //val db = dbClient

  // TODO: db connect
  lifecycle.addStopHook {
    () => Future.successful(/*db.close*/)
  }

  def dbClient = {
    Logger.info("DB initialisation")
    // TODO: db connect
    //Mongo(MongoClient(), "dbtestmp")
  }

}
