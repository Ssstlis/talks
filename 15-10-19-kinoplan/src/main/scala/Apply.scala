trait Apply[F[_]] {
  def ap[A, B]: F[A => B] => F[A] => F[B]
}
