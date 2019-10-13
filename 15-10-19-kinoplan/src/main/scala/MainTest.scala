object MainTest extends App {
  import scala.concurrent.{Future, Await}
  import scala.concurrent.duration.Duration
  import scala.concurrent.ExecutionContext.Implicits.global
  import java.util.concurrent.atomic.AtomicInteger

  val counter = new AtomicInteger(0)

  Await.result(
    for {
      a1 <- Future(counter.get)
      _  <- Future(counter.set(a1 + 1))
      a2 <- Future(counter.get)
    } yield println(a1 + a2), Duration.Inf)
}
