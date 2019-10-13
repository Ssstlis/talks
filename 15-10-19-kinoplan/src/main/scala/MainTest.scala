object MainTest extends App {
  val pfOddInt: PartialFunction[Int, Int] = {
    case int if int % 2 == 0 => int
  }

  println(pfOddInt.apply(3))
}