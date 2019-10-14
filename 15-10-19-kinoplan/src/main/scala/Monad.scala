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

  implicit def functionMonad[Y] = new Monad[Y => ?] {
    def pure[A]: A => Y => A = a => _ => a

    def flatMap[A, B] = a => f => arg => f(a(arg))(arg)
  }

  implicit def myFuncMonad[F[_], Y](implicit ins: Monad[F]) = {
    new Monad[MyFunc[F, Y, ?]] {
      def pure[A] = a => MyFunc(_ => ins.pure(a))

      def flatMap[A, B] = a => f => MyFunc { y =>
        ins.flatMap(a.run(y))(f(_).run(y))
      }
    }
  }
}
