object MainTest extends App {
  val f: Int => Option[Int] = ???
  val g: Int => Option[String] = ???
  val j: String => Option[List[Char]] = ???

  val composed: Int => Option[List[Char]] = {
    f.apply(_).flatMap(v => g(v).flatMap(j))
  }
}