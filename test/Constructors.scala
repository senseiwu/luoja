/**
 * Created by tomek on 2/18/16.
 */


object Fun extends App {

  val sqr1 = (x:Int) => x*x
  def sqr2 = (x:Int) => x*x

  val c = calc {
    println("calc")
    sqr1(3)
  }

  val c2 = calc2(sqr1, 3)
  val c2_1 = calc2_1 {
    println("calling sqr1 here..")
    sqr1
  }
  {
    println(" .. and with param=3")
    3
  }
  val c3 = calc3(() => 44)
  val c4 = calc4(sqr1)(10)
  println("res c=" + c + ", c2=" + c2 + ", c2_1=" + c2_1 + ", c3=" + c3 + ", c4=" + c4(30))

  def calc4(f: (Int) => Int)(x:Int) = {
    f
  }

  def calc3(f:() => Int) = {
    f()*10
  }

  def calc2_1(f:Int=>Int)(x:Int):Int = f(x)

  def calc2(f: Int => Int, x:Int) = {
    f(x)
  }

  def calc(f: => Int) = {
    f
  }

  def sub = calc {
    10
  }

  val i = sub
  println("exec " + i)
}


class Constructors {

}
