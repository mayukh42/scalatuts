package intrigued

/**
 * User: mayukh
 * Date: 11/14/13
 * Time: 11:17 PM
 */

trait Friend {

  val name: String
  def listen () = println("I'm " + name + ", and I'm doing my part")
}

class Human (val name: String) extends Friend

class Animal (val name: String) // this cons duplicacy can also be removed by creating an abstract Mammal class, etc.
class Dog (override val name: String) extends Animal (name) with Friend

class Cat (override val name: String) extends Animal (name)

object TraitTester extends App {

  def seekHelp(friend: Friend) = {
//  all objects that inherit or mix with Friend can be passed to this fn
    println("seeking help")
    friend.listen()
  }

  val sam = new Human("Sam")
  val buddy = new Dog("Buddy")
  val alf = new Cat("Alf")
  val fionaghal = new Cat("Fionaghal") with Friend // an exceptionally friendly cat, one of a kind really

//  sam.listen()
//  buddy.listen()
//  alf.listen() // wont compile, as cat is not a friend
//  fionaghal.listen() // mix traits at instance level. all checked in compile time

  seekHelp(sam)
  seekHelp(buddy)
  seekHelp(fionaghal)

}
