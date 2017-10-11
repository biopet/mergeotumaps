package nl.biopet.tools.mergeotumaps

import java.io.File

import nl.biopet.utils.tool.AbstractOptParser

class ArgsParser(cmdName: String) extends AbstractOptParser[Args](cmdName) {
  opt[File]('I', "input") minOccurs 2 required () unbounded () valueName "<file>" action {
    (x, c) =>
      c.copy(inputFiles = x :: c.inputFiles)
  }
  opt[File]('o', "output") required () unbounded () maxOccurs 1 valueName "<file>" action {
    (x, c) =>
      c.copy(outputFile = x)
  }
  opt[String]('p', "skipPrefix") unbounded () valueName "<text>" action { (x, c) =>
    c.copy(skipPrefix = x :: c.skipPrefix)
  }
}
