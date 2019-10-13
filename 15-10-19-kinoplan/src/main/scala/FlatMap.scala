trait FlatMap[F[_]] extends Apply[F] with Functor[F] {
  def flatMap[A, B]: F[A] => (A => F[B]) => F[B]

  override def ap[A, B]= fa => a => flatMap(fa)(map(a)(_))
}
