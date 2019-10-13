case class MyFunc[F[_], A, B](run: A => F[B]) {
  def compose[C](g: MyFunc[F, C, A]): MyFunc[F, C, B] = ???

  def andThen[C](g: MyFunc[F, B, C]): MyFunc[F, A, C] = {
    g.compose(this)
  }
}