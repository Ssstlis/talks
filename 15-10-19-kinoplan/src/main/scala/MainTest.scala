object MainTest extends App {
  val pf1: PartialFunction[Int, Int] = {
    case int if int % 2 == 0 => int
  }

  val pf2: PartialFunction[Int, String] = {
    case int if int == 4 => "Success"
  }

  val pf3: PartialFunction[Int, String] = pf2 compose pf1

  println(pf3.apply(2))
}