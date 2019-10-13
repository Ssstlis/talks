object MainTest extends App {
  trait A
  trait B { val list: List[A] }
  trait C { val list: List[B] }
  trait D { val list: List[C] }

  val extract: D => List[A] = ???
}