package nl.biopet.tools.mergeotumaps

import java.io.File

import nl.biopet.test.BiopetTest
import org.testng.annotations.Test

import scala.io.Source._

class MergeOtuMapsTest extends BiopetTest {
  @Test
  def testMain(): Unit = {
    val temp = File.createTempFile("out", ".txt")
    temp.deleteOnExit()
    MergeOtuMaps.main(Array("-I", resourcePath("/fakeOtu1"), "-I", resourcePath("/fakeOtu2"),
      "-o", temp.getAbsolutePath(), "-p", "skip_"))
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
