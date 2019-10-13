trait MyFlatMap[F[_]] {
  def flatMap[A, B](a: F[A])(f: A => F[B]): F[B]
}
