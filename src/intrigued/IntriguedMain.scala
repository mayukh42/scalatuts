package intrigued

/**
 * User: mayukh
 * Date: 11/13/13
 * Time: 9:18 PM
 */
object IntriguedMain extends App {

  val ns = List(1,2,3,4,5,6)

  def total_i(xs: List[Int]): Int = {
    var total = 0
    for (i <- xs)
      total += i
    total
  }

  def total_r(xs: List[Int], acc: Int): Int = xs match {
    case y::ys => total_r(ys, acc+y)
    case Nil => acc
  }

//  println(total_i(ns))
//  println(total_r(ns, 0))

  println(
    ns.foldLeft(0) {
      (c, e) => c + e // arg0 (anon fn): c, holds result of the anon fn for the next call. initially, its value = arg0 passed to foldLeft(). arg1 (anon fn) is each element of the collection as foldLeft() traverses it.
    } // foldLeft takes in arg0: init val (identity of fn for the maths nerds), arg1: fn closure (an object that takes a fn block) which has to be applied to each elem in the collection as foldLeft 'rolls over' it
  )

  def doubleList(xs: List[Int]): List[Int] = xs.map(e => e<<1) // Java 8 supports this with almost similar syntax

//  println(doubleList(ns))

  @scala.annotation.tailrec // enforces the fn to be tail call else throws compile time error
  def factorialTail(a: Int, acc: BigInt): BigInt = {
    if (a == 1) acc else factorialTail(a-1, acc*a)
  }

  println(factorialTail(6000, 1)) // ok, almost as good as py, but for the JVM

}
