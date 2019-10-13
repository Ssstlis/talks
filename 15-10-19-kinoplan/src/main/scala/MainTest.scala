object MainTest extends App {
  type =>?[A, B] = A => Option[B]
  def compose[A, B, C](g: B =>? C)(f: A =>? B): A =>? C = f(_).flatMap(g)
  def compose[A, B, C](g: B => Future[C])(f: A => Future[B]): A =>? C = {
    f(_).flatMap(g)
  }

  def compose[A, B, C](g: B => List[C])(f: A => List[B]): A => List[C] = {
    f(_).flatMap(g)
  }

  def compose[A, B, C](g: B => Either[?, C])(f: A => Either[?, B]): A => Either[?, C] = {
    f(_).flatMap(g)
  }
}