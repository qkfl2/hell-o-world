object Ch1  {
	def main(args: Array[String]): Unit = {
			val list1 = List("IOI","AOA","양상현","서로서");
			list1.filter((item) => item == item.reverse).foreach(println);
	}
}
