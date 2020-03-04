import Main.At.Aux

import scala.annotation.tailrec

object Main extends App {

  val foo: Int = 42

  val bar: String = "boom"

  trait A
  trait B extends A

  val baz: Iterable[A] => String = _.toString

  baz(List(new B {}))

  @tailrec
  def tailrec[A, B](init: A)(f: A => Either[B, A]): B = f(init) match {
    case Left(stop)  => stop
    case Right(call) => tailrec(call)(f)
  }

  def atN[A]: List[A] => Int => Option[A] = { lst => n =>
    tailrec((lst, n)) {
      case (head :: _, 0)          => Left(Some(head))
      case (_ :: tail, n) if n > 0 => Right((tail, n - 1))
      case _                       => Left(None)
    }
  }

  def third[A]: List[A] => Option[A] = atN(_)(2)

  assert(third(1 :: 2 :: 3 :: Nil).contains(3))
  assert(third(1 :: 2 :: Nil).isEmpty)
  assert(atN(1 :: 2 :: Nil)(-1).isEmpty)

  sealed trait HList extends Product with Serializable

  final case object HNil extends HList
  final case class :*:[H, L <: HList](head: H, tail: L) extends HList

  sealed trait Nat

  sealed trait _0 extends Nat

  sealed trait Succ[P <: Nat] extends Nat

  val fooH: Int :*: Boolean :*: String :*: Main.HNil.type = //<--Derived type
    :*:(1, :*:(true, :*:("bar", HNil)))

  val thirdH1: String = fooH.tail.tail.head

  val barH: Int :*: Boolean :*: A :*: Main.HNil.type = //<--Derived type
    :*:(1, :*:(true, :*:(new B{}, HNil)))

  val barH1: A = barH.tail.tail.head

  sealed trait At[L <: HList, N <: Nat] extends Serializable {
    type Out //Path-dependent type

    def apply(arg: L): Out
  }

  object At {
    type Aux[L <: HList, N <: Nat, Out0] = At[L, N] {type Out = Out0}

    def apply[L <: HList, N <: Nat](implicit at: At[L, N]): Aux[L, N, at.Out] = at

    implicit def fix[H, L <: HList]: Aux[H :*: L, _0, H] =
      new At[H :*: L, _0] {
        type Out = H

        def apply(arg: H :*: L): H = arg.head
      }

    implicit def next[H, L <: HList, N <: Nat](
      implicit at: At[L, N]
    ): Aux[H :*: L, Succ[N], at.Out] =
      new At[H :*: L, Succ[N]] {
        type Out = at.Out

        def apply(arg: H :*: L): at.Out = at(arg.tail)
      }
  }

  implicit class HListOps[L <: HList](l: L) {
    def atH[N <: Nat](implicit at: At[L, N]): at.Out = at(l)
  }

  def thirdH[L <: HList](l: L)(implicit at: At[L, Succ[Succ[_0]]]): at.Out = at(l)

  type Foo = Succ[Succ[_0]]

  val f: Aux[Int :*: Boolean :*: A :*: Main.HNil.type, Foo, A] =
    At[Int :*: Boolean :*: A :*: Main.HNil.type, Succ[Succ[_0]]]//(next(next(fix)))

  val thirdFoo0: String = fooH.atH[Foo]//(next(next(fix)))
  val thirdFoo1: String = thirdH(fooH)//(next(next(fix)))

  val thirdBar0: A = barH.atH[Foo]//(next(next(fix)))
  val thirdBar1: A = thirdH(barH)//(next(next(fix)))

  //  fooH.atH[Succ[Foo]] doesn't compile
}
