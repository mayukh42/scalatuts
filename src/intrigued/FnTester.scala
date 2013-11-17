package intrigued

/**
 * User: mayukh
 * Date: 11/14/13
 * Time: 10:53 PM
 */
object FnTester extends App {

  val prices = List(10, 15, 20, 25, 30, 35, 40)

  def totalPrices(prices: List[Int]): Int = prices.foldLeft(0)({
    (c, e) => c + e
  })

  def total_universal(prices: List[Int], f: Int => Boolean): Int = prices.foldLeft(0)({
    (c, e) => if (f(e)) (c + e) else c // else part is required as otherwise type of expr is Unit (i.e. LCA of Int and Unit = Unit)
  })

  println(totalPrices(prices))

//  strategy pattern was never so easy
  println(total_universal(prices, (x => true)))

  println(total_universal(prices, (x => x > 20)))

}
