package com.test.week1

import com.test.TestSpec

class MonadTest extends TestSpec {
  /**
   * Data structures with `map` and `flatMap` are common in Scala and
   * functional programming. There is a name for this class of
   * data structures together with some algebraic laws that they
   * `should` have. They are called `monads`. It is the name of
   * a functional design pattern.
   */

  /**
   * A monad `M` is a parametric type `M[T]` with two operations,
   * `flatMap` and `unit` and has to satisfy some laws
   */

  trait M[T] {
    self =>
    def get: T
    def flatMap[U](f:T => M[U]): M[U] // flatMap method is also called 'bind'
    def map[U](f: T => U): M[U] // in scala every monad also has a 'map' function
    def unit(x: T): M[T] // the Monad constructor, most often placed in the companion object of the monad
  }

  /**
   * Monad Laws:
   * To qualify as a Monad, a type has to satisfy three laws:
   *
   * Remember: flatMap accepts a function f: (T => Monad[U]): Monad[U] thus
   * f is T => Monad[U] and returns a Monad[U].
   *
   * Also, 'f' and 'g' are both functions from (T => Monad[U])
   * And: f(x) is an application of the function, and so, the result is Monad[U]
   * And unit(x) is an application of the function (T => Monad[T]): Monad[T] which creates a Monad
   *
   * 1. Associativity:
   * m flatMap f flatMap g == m flatMap (x => f(x) flatMap g
   *
   * Translated: it must not matter whether the evaluation is first (m flatMap f) then flatMap g,
   * or (the right side of the '=='), first apply x to f (which gives a monad), flatMap g, and
   * last m flatMap the evaluated result, thus, it should not matter where the parenthesis
   * are placed. The result must be the same, the sequence of evaluation should not matter
   *
   * 2. Left unit
   * unit(x) flatMap f == f(x)
   *
   * Translated: When you create a Monad (apply x to unit), and then flatMap with function a (T => Monad[U]),
   * it should be the same as applying x to the function f: T => Monad[U], both return a Monad[U].
   * When you do only unit(x) you get a Monad[T], but when you flatMap over that Monad[T] you get a Monad[U].
   * Applying x to f, should also give a Monad[U].
   *
   * For example:
   * assume the function we will use is:
   * val f: Int => Option[Int] = (x: Int) => Option(x * 2)
   * val x = 1
   * (Option(x) flatMap f) == f(x)
   *
   * 3. Right unit
   * m flatMap unit == m
   *
   * When you flatMap with the Unit constructor (which is T => Monad[T]), you don't get a Monad[U], but you get
   * a Monad[T] which is the same as the monad 'm', which is Monad[T]. The monad constructor 'unit' is a
   * function T => Monad[T], which can be applied by flatMap, and gives the monad we started out with.
   * For example, Option(1).flatMap(Option(1)) == Option(1)
   *
   * For example:
   * val x = 1
   * Option(x).flatMap(x => Option(x)) == Option(x)
   *
   * all are true, Option is a Monad
   */
}
