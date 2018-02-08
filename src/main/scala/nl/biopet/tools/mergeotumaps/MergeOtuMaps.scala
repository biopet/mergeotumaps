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

import java.io.PrintWriter

import nl.biopet.utils.tool.ToolCommand

import scala.io.Source

object MergeOtuMaps extends ToolCommand[Args] {
  def emptyArgs: Args = Args()
  def argsParser = new ArgsParser(this)
  def main(args: Array[String]): Unit = {
    val cmdArgs = cmdArrayToArgs(args)

    logger.info("Start")

    mergeOtuMaps(cmdArgs)

    logger.info("Done")
  }

  def mergeOtuMaps(cmdArgs: Args): Unit = {

    var map: Map[String, String] = Map()

    for (inputFile <- cmdArgs.inputFiles) {
      logger.info(s"Start reading $inputFile")
      val reader = Source.fromFile(inputFile)
      reader.getLines().foreach { line =>
        val values = line.split("\t", 2)
        val key = values.head
        if (!cmdArgs.skipPrefix.exists(key.startsWith))
          map += key -> (line.stripPrefix(s"$key") + map.getOrElse(key, ""))
      }
      reader.close()
    }

    logger.info(s"Start writing to ${cmdArgs.outputFile}")
    val writer = new PrintWriter(cmdArgs.outputFile)
    map.foreach { case (key, list) => writer.println(key + list) }
    writer.close()
  }

  def descriptionText: String =
    """
      |This tools merges several OTU maps into one OTU map. It has an option
      |to skip over certain prefixes that should not appear in the merged output
      |file.
    """.stripMargin

  def manualText: String =
    s"""
      |$toolName takes several OTU maps and merges them in a single file.
      |You can ignore certain prefixes in the OTU maps so these entries
      |are not included in the output file using the `-p` flag.
    """.stripMargin

  def exampleText: String =
    s"""
       |To merge two OTU maps and ignore the prefix `seq8` run:
       |
       |${example("-I",
                  "OtuMap1",
                  "-I",
                  "OtuMap2",
                  "-o",
                  "MergedOtuMaps",
                  "-p",
                  "seq8")}
     """.stripMargin
}
