import java.io.File

object HelloWorld {
  def main(args: Array[String]) {
    println(new File(".").listFiles.toList
      .filter(_.getName.matches("[1-3].txt"))
      .flatMap((it:File) => io.Source.fromFile(it.getPath).getLines)
      .groupBy((it:String) => it.substring(it.lastIndexOf("@")))
      .mapValues(_.size))
  }
}
