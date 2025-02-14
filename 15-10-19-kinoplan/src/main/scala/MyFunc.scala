case class MyFunc[F[_], A, B](run: A => F[B]) {
  def compose[C](g: MyFunc[F, C, A])(implicit ins: FlatMap[F]): MyFunc[F, C, B] = {
    MyFunc(c => ins.flatMap(g.run(c))(run))
  }

  def andThen[C](g: MyFunc[F, B, C])(implicit ins: FlatMap[F]): MyFunc[F, A, C] = {
    g.compose(this)
  }
}