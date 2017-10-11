package nl.biopet.tools.mergeotumaps

import nl.biopet.test.BiopetTest
import org.testng.annotations.Test

class MergeOtuMapsTest extends BiopetTest {
  @Test
  def testNoArgs(): Unit = {
    intercept[IllegalArgumentException] {
      MergeOtuMaps.main(Array())
    }
  }
}
