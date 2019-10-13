object MainTest extends App {
  val f: Int => Option[Int] = ???
  val g: Int => Option[String] = ???
  val composed: Int => Option[String] = f.apply(_).flatMap(g)
}