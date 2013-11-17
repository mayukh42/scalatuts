package intrigued

/**
 * User: mayukh
 * Date: 11/14/13
 * Time: 10:40 PM
 */
class Car (val yom: Int, var odo: Int) {

  println("created...")

  def drive (miles: Int) {
    println("driving...")
    odo += miles
  }

}

object Car {

  def create(year: Int) = new Car (year, 5)
}


object CarTest extends App {

  val car1 = Car create 2013 // wrapper cons. object created is that of a Car class, but the 'Car' itself is a singleton.
//  println(car1.yom)
//  println(car1.odo)
}
