object MainTest extends App {
  import cats.effect.IO
  import java.util.concurrent.atomic.AtomicInteger

  val counter = new AtomicInteger(0)
  val get = IO.delay(counter.get)

  (for {
    a1 <- get
    _  <- IO.delay(counter.set(a1 + 1))
    a2 <- get
    _  <- IO.delay(println(a1 + a2))
  } yield ()).unsafeRunSync()
}