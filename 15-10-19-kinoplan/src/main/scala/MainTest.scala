object MainTest extends App {

  def compose[A, B, C, F[_]](g: B => F[C])(f: A => F[B])(
    implicit ins: MyFlatMap[F]
  ): A => F[C] = v => ins.flatMap(f(v))(g)
}