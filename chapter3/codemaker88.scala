import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.Path
import scala.collection.JavaConverters
import scala.collection.Iterator

class Chapter3 {
  def solve() {
    var javaStream = Files.list(Paths.get("./input/Chapter3"))
    var scalaIteratorConverter = JavaConverters.asScalaIteratorConverter(javaStream.iterator())
    scalaIteratorConverter.asScala
    .flatMap(readData)
    .toList.groupBy(getDomain)
    .foreach((x) => println(x._1 + " : " + x._2.size))
  }

  def readData(path : Path) : Iterator[String] = {
    return JavaConverters.asScalaIteratorConverter(Files.lines(path).iterator()).asScala
  }
  
  def getDomain(email : String) : String = {
    return email.substring(email.indexOf("@") + 1)
  }
  
  def partialFunction(email : String, result : String) : String = {
    return getDomain(email) 
  }
}