package nl.biopet.tools.mergeotumaps

import java.io.File

import nl.biopet.utils.tool.{AbstractOptParser, ToolCommand}

class ArgsParser(toolCommand: ToolCommand[Args])
    extends AbstractOptParser[Args](toolCommand) {
  opt[File]('I', "input") minOccurs 2 required () unbounded () valueName "<file>" action {
    (x, c) =>
      c.copy(inputFiles = x :: c.inputFiles)
  } text
    """Input OTU map. Flag needs to be specified at least 2 times: -I OTUmap1 -I OTUmap2.
      |There is no limit to the number of OTU maps that can be merged.""".stripMargin
  opt[File]('o', "output") required () maxOccurs 1 valueName "<file>" action {
    (x, c) =>
      c.copy(outputFile = x)
  } text "Output OTU map file."
  opt[String]('p', "skipPrefix") unbounded () valueName "<text>" action {
    (x, c) =>
      c.copy(skipPrefix = x :: c.skipPrefix)
  } text "Prefix that will not be included in the output file. Flag can be used multiple times."
}
