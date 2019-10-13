trait Monad[F[_]] extends Applicative[F] with FlatMap[F] {
  override def map[A, B] = a => f => flatMap(a)(v => pure(f(v)))

  override def ap[A, B] = fa => a => flatMap(fa)(map(a)(_))
}

object Monad {
  implicit val optionMonad = new Monad[Option] {
    def flatMap[A, B] = a => f => a.flatMap(f)

    def pure[A] = Some(_)
  }

  implicit val listMonad = new Monad[List] {
    def flatMap[A, B] = a => f => a.flatMap(f)

    def pure[A] = List(_)
  }
}
