object Chapter1 {
  def main(args: Array[String]) {
    var list = List("wow1", "level", "AOA", "I.O.I");
    list.filter(s => s.equals(s.reverse)).foreach(s => println(s)); 
  }
}
