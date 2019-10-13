object MainTest extends App {
  def compose[A, B, C, F[_]](g: B => F[C])(f: A => F[B])(
    implicit ins: MyFlatMap[F]
  ): A => F[C] = v => ins.flatMap(f(v))(g)

  val f: Int => Option[Int] = ???
  val g: Int => Option[String] = ???
  val j: String => Option[List[Char]] = ???

  val composed: Int => Option[List[Char]] = compose(j)(compose(g)(f))
}