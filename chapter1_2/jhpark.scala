object Chapter1 {
  def main(args: Array[String]) {
    List("winter", "is", "coming", "I.O.I", "2NE1", "2NN2", "AOA", "wow", "level")
      .filter((text: String) => text == text.reverse)
      .foreach(println)
  }
}
