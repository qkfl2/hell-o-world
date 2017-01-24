
object Chapter3 {
  def main(args: Array[String]) {
    var a = scala.io.Source.fromFile("D:/scala-SDK-4.5.0-vfinal-2.11-win32.win32.x86_64/chapter1/src/1.txt");
    var b = scala.io.Source.fromFile("D:/scala-SDK-4.5.0-vfinal-2.11-win32.win32.x86_64/chapter1/src/2.txt");
    var c = scala.io.Source.fromFile("D:/scala-SDK-4.5.0-vfinal-2.11-win32.win32.x86_64/chapter1/src/3.txt");
    var list = List(a, b, c);
    list.flatMap(source => source.getLines()).groupBy(k => k.substring(k.indexOf("@") + 1)).foreach(s => println(s._1 + " : " + s._2.size));
  }
}
