package nl.biopet.tools.mergeotumaps

import nl.biopet.test.BiopetTest
import org.testng.annotations.Test

object MergeOtuMapsTest extends BiopetTest {
  @Test
  def testNoArgs(): Unit = {
    intercept[IllegalArgumentException] {
      ToolTemplate.main(Array())
    }
  }
}
