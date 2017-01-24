package chapter3

import java.util

import scala.io.Source


/**
  * Created by bross on 2017. 1. 24..
  */
object Chapter3 {
  var paretPath = "/Users/bross/Documents/Coding/Scala/src/chapter3/files";

  def main(args: Array[String]): Unit = {


    var hashMap: util.HashMap[String, Int] = new util.HashMap[String, Int];

    new java.io.File(paretPath).listFiles.filter(_.getName.endsWith(".txt")).foreach(file => {

      Source.fromFile(file).getLines().map(getDomain).foreach(key => {
        if (hashMap.get(key) == null) {
          hashMap.put(key, 1);
        }
        else {
          hashMap.replace(key, hashMap.get(key) + 1);
        }
      })
    })

    hashMap.keySet().forEach(key => {
      println(key + " : " + hashMap.get(key));
    })
  }

  def getDomain(text: String): String = {
    text.substring(text.indexOf("@") + 1, text.indexOf(".com"));
  }
}
