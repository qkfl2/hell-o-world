object Chapter4 {
  class 비빔밥 {  def 비비기(s: String): String = { s + "비빔밥" } }

  trait 고추장 extends 비빔밥 { override def 비비기(s: String): String = { super.비비기("고추장 " + s) } }
  trait 초장 extends 비빔밥 { override def 비비기(s: String): String = { super.비비기("초장 " + s) } }
  trait 된장 extends 비빔밥 { override def 비비기(s: String): String = { super.비비기("된장 " + s) } }
  trait 소주 extends 비빔밥 { override def 비비기(s: String): String = { super.비비기("소주 " + s) } }

  def main(args: Array[String]): Unit = {
    val 저녁밥 = new 비빔밥 with 고추장 with 된장 with 소주
    println(저녁밥 비비기 "")
  }
}
