import play.api.libs.iteratee.{Enumerator, Iteratee}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
/**
 * Created by tomek on 2/15/16.
 */
object MyIntereteeTest extends App {

    val summingIteratee = Iteratee.fold(0){ (sum: Int, chunk: Int) =>
      println("Summing " + sum + ", " + chunk)
      sum + chunk
    }

    val intEnumerator = Enumerator(44,6,3,7,3,1,1,9)
    val newIterateeFuture: Future[Iteratee[Int, Int]] = intEnumerator(summingIteratee)
    val resultFuture: Future[Int] = newIterateeFuture.flatMap(_.run)

    resultFuture.onComplete(sum => println("The sum is %d" format sum))
    println("..")

}
