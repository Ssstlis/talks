object MainTest extends App {
  import Monad._

  val f: MyFunc[Option, Int, Int] = ???
  val g: MyFunc[Option, Int, String] = ???
  val j: MyFunc[Option, String, List[Char]] = ???

  val composed: MyFunc[Option, Int, List[Char]] = j.compose(g.compose(f))
}