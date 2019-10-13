object MainTest extends App {
  type =>?[A, B] = A => Option[B]
  def compose[A, B, C](g: B =>? C)(f: A =>? B): A =>? C = f(_).flatMap(g)
  def compose[A, B, C](g: B => List[C])(f: A => List[B]): A => List[C] = {
    f.apply(_).flatMap(g)
  }
}