trait FlatMap[F[_]] extends Apply[F] with Functor[F] {
  def flatMap[A, B]: F[A] => (A => F[B]) => F[B]

  override def ap[A, B]= fa => a => flatMap(fa)(map(a)(_))
}

object FlatMap {
  implicit def mapFlatMap[K] = new FlatMap[Map[K, ?]] {
    def flatMap[A, B] = a => f => a.flatMap { case (k, v) =>
      f(v).get(k).map(k -> _)
    }

    def map[A, B] = a => f => a.map { case (k, v) => (k, f(v)) }
  }
}
