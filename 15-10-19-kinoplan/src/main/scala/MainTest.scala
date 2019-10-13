object MainTest extends App {
  val a: Int => Boolean = _ % 2 == 0
  val b: Boolean => String = b => s"?$b?"
  val c: String => String = s => s"\\$s\\"
}