/**
 * Created by tomek on 2/9/16.
 */
class Foo {
  def foo {
    print("foo")
  }
}

class Bar {
  def bar {
    print("bar")
  }
}

object Test
{
  implicit def fooToBarConverter(foo: Foo) = {
    print("before ")
    foo.foo
    print(" after ")
    new Bar
  }

  def main(args: Array[String]) {
    val foo = new Foo
    foo.foo
    val f = implicitly[Foo=>Bar]
    println(">>: " + f)
    foo.bar
  }
}
