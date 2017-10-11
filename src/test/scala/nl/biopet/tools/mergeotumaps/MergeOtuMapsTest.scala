package nl.biopet.tools.mergeotumaps

import java.io.File
import java.nio.file.Paths

import nl.biopet.test.BiopetTest
import org.testng.annotations.Test

class MergeOtuMapsTest extends BiopetTest {
  private def resourcePath(p: String): String = {
    Paths.get(getClass.getResource(p).toURI).toString
  }

  @Test
  def testMain(): Unit = {
    val temp = File.createTempFile("out", ".txt")
    temp.deleteOnExit()
    MergeOtuMaps.main(Array("-I", resourcePath("fakeOtu1"), "-I", resourcePath("fakeOtu2"), "-o", temp.getAbsolutePath()))
  }

  @Test
  def testNoArgs(): Unit = {
    intercept[IllegalArgumentException] {
      MergeOtuMaps.main(Array())
    }
  }
}
