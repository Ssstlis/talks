trait Monad[F[_]] extends Applicative[F] {
  def flatMap[A, B]: F[A] => (A => F[B]) => F[B]

  override def map[A, B]: F[A] => (A => B) => F[B] = a => f => {
    flatMap(a)(v => pure(f(v)))
  }

  override def ap[A, B]: F[A => B] => F[A] => F[B] = fa => a => {
    flatMap(fa)(map(a)(_))
  }
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
