package b2b.model

import java.sql.Timestamp

/**
  * Created by tomek on 6/5/16.
  */

case class Item(id:Long, sourceId:Long, askPx:Double, tag:String, itemType:Int,
                created:Timestamp, updated:Timestamp, isActive:Boolean)
