object MainTest extends App {
  val a: Int => Boolean = _ % 2 == 0
  val b: Boolean => String = b => s"?$b?"
  val c: String => String = s => s"\\$s\\"

  val d: Int => String = c compose b compose a

  println(d.apply(2))
  println(d.apply(1))
}