trait Functor[F[_]] {
  def map[A, B]: F[A] => (A => B) => F[B]
}