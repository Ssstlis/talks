trait Arrow[F[_, _]] {
  def lift[A, B]: (A => B) => F[A, B]

  def compose[A, B, C]: F[B, C] => F[A, B] => F[A, C]

  def andThen[A, B, C]: F[A, B] => F[B, C] => F[A, C] = {
    fa => compose(_)(fa)
  }

  def first[A, B, C]: F[A, B] => F[(A, C), (B, C)]

  def second[A, B, C]: F[A, B] => F[(C, A), (C, B)]
}
