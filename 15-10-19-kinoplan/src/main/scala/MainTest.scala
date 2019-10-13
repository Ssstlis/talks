import cats.effect.concurrent.Ref
import cats.effect.{ExitCode, IO, IOApp}

object MainTest extends IOApp {
  def run(args: List[String]): IO[ExitCode] = {
    for {
      ref <- Ref[IO].of(0)
      a1  <- ref.modify(a => (a + 1, a))
      a2  <- ref.get
      _   <- IO.delay(println(a1 + a2))
    } yield ExitCode.Success
  }
}