case class MyFunc[F[_], A, B](run: A => F[B]) {
  def compose[C](g: MyFunc[F, C, A]): MyFunc[F, C, B] = ???
}