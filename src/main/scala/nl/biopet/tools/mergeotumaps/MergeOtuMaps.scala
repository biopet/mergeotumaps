package nl.biopet.tools.mergeotumaps

import java.io.PrintWriter

import nl.biopet.utils.tool.ToolCommand

import scala.io.Source

object MergeOtuMaps extends ToolCommand[Args] {
  def main(args: Array[String]): Unit = {
    val parser = new ArgsParser(toolName)
    val cmdArgs =
      parser.parse(args, Args()).getOrElse(throw new IllegalArgumentException)

    logger.info("Start")

    mergeOtuMaps(cmdArgs)

    logger.info("Done")
  }

  def mergeOtuMaps(cmdArgs: Args): Unit ={

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
}
