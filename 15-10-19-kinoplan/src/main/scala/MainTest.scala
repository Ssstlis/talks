object MainTest extends App {
  val pf1: PartialFunction[Int, Int] = {
    case int if int % 2 == 0 => int
  }

  val pf2: PartialFunction[Int, String] = {
    case int if int == 4 => "Success"
  }

  val pfLifted1: Int => Option[Int] = pf1.lift
  val pfLifted2: Int => Option[String] = pf2.lift
  val pfLifted3: Int => Option[String] = pfLifted2 compose pfLifted1

}