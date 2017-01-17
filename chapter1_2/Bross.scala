package chapter1_2
/**
  * Created by bross on 2017. 1. 17..
  */
object HelloWorld {
  def main(args: Array[String]): Unit = {
    val stringArray = Array("wow", "level", "AOA", "I.O.I", "apple");
    stringArray.filter(strItem => {
      strItem.equals(strItem.reverse)
    }).foreach(println)
  }
}
