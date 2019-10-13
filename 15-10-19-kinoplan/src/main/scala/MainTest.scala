object MainTest extends App {
  import scala.concurrent.{Future, Await}
  import scala.concurrent.duration.Duration
  import scala.concurrent.ExecutionContext.Implicits.global
  import java.util.concurrent.atomic.AtomicInteger

  val counter = new AtomicInteger(0)
  val get = Future(counter.get)

  Await.result(
    for {
      a1 <- get
      _  <- Future(counter.set(a1 + 1))
      a2 <- get
    } yield println(a1 + a2), Duration.Inf)
}
