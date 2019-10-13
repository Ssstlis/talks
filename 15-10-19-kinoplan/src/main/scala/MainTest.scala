object MainTest extends App {
  trait A
  trait B { val list: List[A] }
  trait C { val list: List[B] }
  trait D { val list: List[C] }

  def compose[A, B, C](g: B => List[C])(f: A => List[B]): A => List[C] = {
    a => f.apply(a).flatMap(g)
  }

  val d2c: D => List[C] = _.list
  val c2b: C => List[B] = _.list
  val b2a: B => List[A] = _.list

  val extract: D => List[A] = compose(b2a)(compose(c2b)(d2c))
}