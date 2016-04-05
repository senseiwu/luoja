
import services.Mongo
import services.Schema
import models.Event
import org.scalatest.BeforeAndAfter
import org.scalatest.{BeforeAndAfter, FunSuite}

/**
 * Created by tomek on 2/2/16.
 */

trait Car {
  def show(implicit data:Int):Int = {
    println("Data: " + data)
    data
  }
}

object A {
  implicit val a:Int = 4
  implicit val s:String = "55"
  implicit def cvt(s:String):Int = {
    println("implicit fun called")
    Integer.parseInt(s)
  }

}

class Bmw extends Car {
  def convert(s:String)(implicit cvt:(String) => (Int)):Int = cvt(s)
  def conv2(s:String):Int = Integer.valueOf(s)
}

class UserTest extends FunSuite with BeforeAndAfter {
  val m = Map(1 -> "11", 2 -> "22", 3 -> "33")
  val l:List[String] = m.map(_._2).asInstanceOf[List[String]]
  l.foreach(println)
}

class EventTest extends FunSuite with BeforeAndAfter {

  test("implicit val test") {
    //implicit val a:Int = 4
    import A._
    val car = new Bmw
    val i1 = car.show
    val i2 = car.show(5)
    assert(4 == i1)
    assert(5 == i2)
    assert(55 == car.convert(A.s))
//    A.cvt(new String("33"))
//    Integer.valueOf("44")
//    car.conv2(A.s)
  }
//
//  test("check today") {
//    assert(2 == Event.findAllToday.size)
//    assert(4 == Event.findAllUpcoming.size)
//    assert(3 == Event.findAllPast.size)
//  }
//
//  test("josy") {
//    val ev = Event.events
//    val evs = ev.toList.sortWith(_.when.toDate.getTime > _.when.toDate.getTime)
//    evs.foreach(println)
//
//  }

//  test("services test") {
//    val mongo = Mongo(MongoClient(), "dbtestmp")
//    val db = mongo.getdb
//    db.dropDatabase()
//    val users = mongo.collection("users")
//    val lectures = mongo.collection("lecture")
//    val events = mongo.collection("event")

    // USERS
//    val userObj1 = Schema.userMongoObj("tomek1", "tomek1@go2.pl", "Tomasz", "Kozlowski", "#$Hjhkjh41", "CTO", "London", List())
//    val userObj2 = Schema.userMongoObj("tomek2", "tomek2@go2.pl", "Tomasz", "Kozlowski", "#$Hjhkjh42", "CTO", "London", List())
//    val userObj3 = Schema.userMongoObj("tomek3", "tomek3@go2.pl", "Tomasz", "Kozlowski", "#$Hjhkjh43", "CTO", "London", List())
//    val userObj4 = Schema.userMongoObj("tomek4", "tomek4@go2.pl", "Tomasz", "Kozlowski", "#$Hjhkjh44", "CTO", "London", List())

//    users.insert(userObj1)

//    val cursor = users.findOne("name" $eq "tomek11")
//    println("colls: " + db.collectionNames() + " .. cursor " + users.size + ", " + users.find + " .. " + cursor)
//
//    if(!cursor.isDefined) fail()
//
//    println("job_position: " + cursor.get.get("job_position"))
//    val id = cursor.get.get("_id").asInstanceOf[ObjectId]
//    println("UserID: " + id)
//
//    // LECTURES
//    val lectureObj = Schema.lectureMongoObj("JVM", "bla bla bla", id, 2, List("Java", "JVM"))
//    lectures.insert(lectureObj)
//    val lecture = lectures.find("speaker" $eq id)
//    println("User's lectures: " + lecture.size)
//    lecture.foreach(println)

    // EVENTS
//    val evObj = Schema.eventMongoObj("JUGSZ", "Java etc", id, null, List(), null, List(), null)
//    events.insert(evObj)
//    println("EvID: " + evObj._id + ", " + events.findOne("name" $eq "JUGSZ"))
//  }

}
