trait Applicative[F[_]] extends Functor[F] {
  def pure[A]: A => F[A]

  def ap[A, B]: F[A => B] => F[A] => F[B]

  override def map[A, B] = a => f => ap(pure(f))(a)
}
