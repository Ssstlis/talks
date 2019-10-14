trait Applicative[F[_]] extends Apply[F] with Functor[F] {
  def pure[A]: A => F[A]

  override def map[A, B] = a => f => ap(pure(f))(a)
}
