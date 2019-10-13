trait Arrow[F[_, _]] {
  def lift[A, B]: (A => B) => F[A, B]

  def compose[A, B, C]: F[B, C] => F[A, B] => F[A, C]

  def andThen[A, B, C]: F[A, B] => F[B, C] => F[A, C] = {
    fa => compose(_)(fa)
  }

  def first[A, B, C]: F[A, B] => F[(A, C), (B, C)]

  def second[A, B, C]: F[A, B] => F[(C, A), (C, B)]
}

object Arrow {
  implicit val functionArrow = new Arrow[Function1] {
    def lift[A, B] = identity

    def compose[A, B, C] = g => f => g compose f

    def first[A, B, C] = f => { case (a, c) => (f(a), c) }

    def second[A, B, C] = f => { case (c, a) => (c, f(a)) }
  }

  implicit def myFuncArrow[F[_]](implicit ins: Monad[F]) = {
    new Arrow[MyFunc[F, ?, ?]] {
      def lift[A, B] = f => MyFunc(arg => ins.pure(f(arg)))

      def compose[A, B, C] = g => f => MyFunc { arg =>
        ins.flatMap(f.run(arg))(g.run)
      }

      def first[A, B, C] = f => MyFunc {
        case (a, c) => ins.map(f.run(a))((_, c))
      }

      def second[A, B, C] = f => MyFunc {
        case (c, a) => ins.map(f.run(a))((c, _))
      }
    }
  }
}
