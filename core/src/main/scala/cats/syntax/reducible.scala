/*
 * Copyright (c) 2022 Typelevel
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package cats
package syntax

trait ReducibleSyntax extends Reducible.ToReducibleOps {
  implicit final def catsSyntaxNestedReducible[F[_]: Reducible, G[_], A](fga: F[G[A]]): NestedReducibleOps[F, G, A] =
    new NestedReducibleOps[F, G, A](fga)
}

final class NestedReducibleOps[F[_], G[_], A](private val fga: F[G[A]]) extends AnyVal {
  def reduceK(implicit F: Reducible[F], G: SemigroupK[G]): G[A] = F.reduceK(fga)
}

private[syntax] trait ReducibleSyntaxBinCompat0 {
  implicit final def catsSyntaxReducibleOps0[F[_], A](fa: F[A]): ReducibleOps0[F, A] =
    new ReducibleOps0[F, A](fa)
}

final class ReducibleOps0[F[_], A](private val fa: F[A]) extends AnyVal {

  /**
   * Apply `f` to each element of `fa` and combine them using the
   * given `SemigroupK[G]`.
   *
   * {{{
   * scala> import cats._, cats.data._, cats.implicits._
   * scala> val f: Int => Endo[String] = i => (s => s + i)
   * scala> val x: Endo[String] = NonEmptyList.of(1, 2, 3).reduceMapK(f)
   * scala> val a = x("foo")
   * a: String = "foo321"
   * }}}
   */
  def reduceMapK[G[_], B](f: A => G[B])(implicit F: Reducible[F], G: SemigroupK[G]): G[B] = F.reduceMapK[G, A, B](fa)(f)

  def reduceA[G[_], B](implicit F: Reducible[F], ev: A <:< G[B], G: Apply[G], B: Semigroup[B]): G[B] =
    F.reduceA[G, B](fa.asInstanceOf[F[G[B]]])
}
