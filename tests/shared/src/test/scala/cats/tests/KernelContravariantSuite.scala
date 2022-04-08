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

package cats.tests

import cats.{Contravariant, ContravariantSemigroupal, Invariant, Semigroupal}
import cats.kernel.{Eq, Hash, Order, PartialOrder}
import cats.laws.discipline.arbitrary._
import cats.laws.discipline._
import cats.laws.discipline.eq._

class KernelContravariantSuite extends CatsSuite {
  Invariant[Eq]
  Contravariant[Eq]
  Semigroupal[Eq]
  ContravariantSemigroupal[Eq]
  checkAll("Contravariant[Eq]", ContravariantTests[Eq].contravariant[MiniInt, Int, Boolean])
  checkAll("Semigroupal[Eq]", SemigroupalTests[Eq].semigroupal[MiniInt, Boolean, Boolean])
  checkAll("Contravariant[Eq]", SerializableTests.serializable(Contravariant[Eq]))

  Invariant[PartialOrder]
  Contravariant[PartialOrder]
  Semigroupal[PartialOrder]
  ContravariantSemigroupal[PartialOrder]
  checkAll("Contravariant[PartialOrder]", ContravariantTests[PartialOrder].contravariant[MiniInt, Int, Boolean])
  checkAll("Semigroupal[PartialOrder]", SemigroupalTests[PartialOrder].semigroupal[MiniInt, Boolean, Boolean])
  checkAll("Contravariant[PartialOrder]", SerializableTests.serializable(Contravariant[PartialOrder]))

  Invariant[Order]
  Contravariant[Order]
  Semigroupal[Order]
  ContravariantSemigroupal[Order]
  checkAll("Contravariant[Order]", ContravariantTests[Order].contravariant[MiniInt, Int, Boolean])
  checkAll("Semigroupal[Order]", SemigroupalTests[Order].semigroupal[MiniInt, Boolean, Boolean])
  checkAll("Contravariant[Order]", SerializableTests.serializable(Contravariant[Order]))

  Contravariant[Hash]
  checkAll("Contravariant[Hash]", ContravariantTests[Hash].contravariant[MiniInt, Int, Boolean])
  checkAll("Contravariant[Hash]", SerializableTests.serializable(Contravariant[Hash]))
}
