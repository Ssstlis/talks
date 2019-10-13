object MainTest extends App {
  type =>?[A, B] = A => Option[B]
  
  val f: Int =>? Int = ???
  val g: Int =>? String = ???
  val j: String =>? List[Char] = ???

  def compose[A, B, C](f: B =>? C)(g: A =>? B): A =>? C = {
    v => g.apply(v).flatMap(f)
  }

  val composed: Int =>? List[Char] = compose(j)(compose(g)(f))
}