/*
 * Copyright (c) 2014 Biopet
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

package nl.biopet.tools.mergeotumaps

import java.io.File

import nl.biopet.utils.test.tools.ToolTest
import org.testng.annotations.Test

import scala.io.Source._

class MergeOtuMapsTest extends ToolTest[Args] {
  def toolCommand: MergeOtuMaps.type = MergeOtuMaps
  @Test
  def testMain(): Unit = {
    val temp = File.createTempFile("out", ".txt")
    temp.deleteOnExit()
    MergeOtuMaps.main(
      Array("-I",
            resourcePath("/fakeOtu1"),
            "-I",
            resourcePath("/fakeOtu2"),
            "-o",
            temp.getAbsolutePath(),
            "-p",
            "skip_"))
    val contents = fromFile(temp).mkString
    val expected = fromFile(resourcePath("/outOtu")).mkString
    contents shouldBe expected
  }

  @Test
  def testNoArgs(): Unit = {
    intercept[IllegalArgumentException] {
      MergeOtuMaps.main(Array())
    }
  }
}
