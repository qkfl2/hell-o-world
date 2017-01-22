
object Chapter1 {
  def solve() {
    val list = List("wow", "AOA", "IOI", "TEST")
    list.filter((x) => x.equals(x.reverse))
    .foreach((x) => println(x))
  }
}