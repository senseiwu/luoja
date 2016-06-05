package b2b.model

import java.sql.Timestamp

/**
  * Created by tomek on 6/5/16.
  */

case class Order(id:Long, clientId:Long, itemType:Int, itemTag:String, bidPx:Double,
                 created:Timestamp, updated:Timestamp, hasDeal:Boolean)
