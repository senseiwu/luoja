package b2b.model

import java.sql.Timestamp

/**
  * Created by tomek on 6/5/16.
  */

case class Deal(id:Long, parentId:Long, created:Timestamp, updated:Timestamp,
                clientId:Long, itemId:Long, orderId:Long)
case class DealDone(id:Long, parentId:Long, created:Timestamp, comment:String)
case class DealRej(id:Long, parentId:Long, created:Timestamp, comment:String)
