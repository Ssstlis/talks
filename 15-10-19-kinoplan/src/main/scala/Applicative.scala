trait Applicative[F[_]] {
  def pure[A]: A => F[A]

  def ap[A, B]: F[A => B] => F[A] => F[B]
}
