trait MyFlatMap[F[_]] {
  def flatMap[A, B](a: F[A])(f: A => F[B]): F[B]
}

object MyFlatMap {
  implicit val optionFlatMap = new MyFlatMap[Option] {
    def flatMap[A, B](a: Option[A])(f: A => Option[B]) = {
      a.flatMap(f)
    }
  }

  implicit val listMyFlatMap = new MyFlatMap[List] {
    def flatMap[A, B](a: List[A])(f: A => List[B]) = {
      a.flatMap(f)
    }
  }

  implicit def mapMyFlatMap[K] = new MyFlatMap[Map[K, ?]] {
    def flatMap[A, B](a: Map[K, A])(f: A => Map[K, B]) = {
      a.flatMap { case (k, v) => f(v).get(k).map(k -> _) }
    }
  }
}
