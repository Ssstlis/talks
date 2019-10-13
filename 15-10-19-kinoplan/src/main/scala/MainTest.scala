object MainTest extends App {
  val pfOddInt: PartialFunction[Int, Int] = {
    case int if int % 2 == 0 => int
  }

  val lifted: Int => Option[Int] = pfOddInt.lift

  println(lifted.apply(3))
}