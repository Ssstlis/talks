trait Monad[F[_]] {
  def flatMap[A, B]: F[A] => (A => F[B]) => F[B]
}

object Monad {
  implicit val optionMonad = new Monad[Option] {
    def flatMap[A, B] = a => f => a.flatMap(f)
  }

  implicit val listMonad = new Monad[List] {
    def flatMap[A, B] = a => f => a.flatMap(f)
  }

  implicit def mapMonad[K] = new Monad[Map[K, ?]] {
    def flatMap[A, B] = a => f => a.flatMap { case (k, v) =>
      f(v).get(k).map(k -> _)
    }
  }
}
