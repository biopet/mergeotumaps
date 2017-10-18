package nl.biopet.tools.mergeotumaps

import java.io.File

case class Args(inputFiles: List[File] = Nil,
                outputFile: File = null,
                skipPrefix: List[String] = Nil)
