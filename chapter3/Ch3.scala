import java.io.File
import scala.io.Source

object Ch2  {
	def main(args: Array[String]): Unit = {
			getFileList("C:\\tmp").flatMap(readData)
			.toList.groupBy(getDomain).foreach((x)=> println(x._1 + " : " + x._2.length))
	}
	
	def readData(t : File) : Iterator[String] = {
    Source.fromFile(t.getPath()).getLines
	}
	def getFileList(dir: String):List[File] = {
			val d = new File(dir)
			d.listFiles.toList
	}
	
	def getDomain(email : String):String = {
	  email.split("@")(1)
	}
}
