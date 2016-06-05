package b2b

import java.sql.Timestamp

import b2b.dao.{DealDAO, ItemDAO, OrderDAO}
import b2b.model.Order
import com.google.inject.Inject

/**
  * Created by tomek on 6/4/16.
  */
class B2BService @Inject() (order:OrderDAO, item:ItemDAO, deal:DealDAO) {
  val now = new Timestamp(System.currentTimeMillis())
  order.insert(Order(0, 12345,2,"security",10000.9,now,now,false))
}
