object MainTest extends App {
  def compose[A, B, C, F[_]](g: B => F[C])(f: A => F[B])(
    implicit ins: Monad[F]
  ): A => F[C] = v => ins.flatMap(f(v))(g)

  trait A
  trait B { val list: List[A] }
  trait C { val list: List[B] }
  trait D { val list: List[C] }

  val d2c: D => List[C] = _.list
  val c2b: C => List[B] = _.list
  val b2a: B => List[A] = _.list

  val extract: D => List[A] = compose(b2a)(compose(c2b)(d2c))
}