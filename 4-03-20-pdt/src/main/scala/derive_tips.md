```
At[Int :*: Boolean :*: A :*: Main.HNil.type, Succ[Succ[_0]]]
=
- fix[Int, Boolean :*: A :*: Main.HNil.type, _0, Int] ~ _0 != Succ[Succ[_0]]
+ next[Int, Boolean :*: A :*: Main.HNil.type, Succ[_0]]: 
  At.Aux[Int :*: Boolean :*: A :*: Main.HNil.type, Succ[Succ[_0]], ?]
  - fix[Boolean, A :*: Main.HNil.type, _0, Int] ~ _0 != Succ[_0]
  + next[Boolean, A :*: Main.HNil.type, Succ[_0]:
    At.Aux[Boolean, A :*: Main.HNil.type, Succ[_0], ?]
      - next[A, Main.HNil.type, _0: N] ~ N != Succ[?]
      + fix[A, Main.HNil.type]
        At.Aux[A, Main.HNil.type, _0, A]
=
next(next(fix))
```
