case class MyFunc[F[_], A, B](run: A => F[B]) {
  def compose[C](g: MyFunc[F, C, A])(implicit ins: Monad[F]): MyFunc[F, C, B] = {
    MyFunc(c => ins.flatMap(g.run(c))(run))
  }

  def andThen[C](g: MyFunc[F, B, C])(implicit ins: Monad[F]): MyFunc[F, A, C] = {
    g.compose(this)
  }

  def map[C](f: B => C)(implicit ins: Functor[F]): MyFunc[F, A, C] = {
    MyFunc(a => ins.map(run(a))(f))
  }
}