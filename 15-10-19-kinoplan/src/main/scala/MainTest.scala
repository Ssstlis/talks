object MainTest extends App {
  import scala.concurrent.{Future, Await}
  import scala.concurrent.duration.Duration
  import scala.concurrent.ExecutionContext.Implicits.global

  def compute1 = { println("1") ; 1 }
  def compute2 = { println("2") ; 2 }

  def fa = Future(compute1)
  def fb = Future(compute2)

  Await.result(
    for {
      a1 <- fa
      a2 <- fa
      b1 <- fb
      b2 <- fb
    } yield println(a1 + a2 + b1 + b2), Duration.Inf)
}
