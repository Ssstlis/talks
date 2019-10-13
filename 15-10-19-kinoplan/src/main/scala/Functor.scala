trait Functor[F[_]] {
  def map[A, B]: F[A] => (A => B) => F[B]
}

object Functor {
  implicit val optionFunctor = new Functor[Option] {
    def map[A, B] = a => f => a.map(f)
  }

  implicit val listFunctor = new Functor[List] {
    def map[A, B] = a => f => a.map(f)
  }

  implicit def mapFunctor[K] = new Functor[Map[K, ?]] {
    def map[A, B] = a => f => a.map { case (k, v) => (k, f(v)) }
  }
}