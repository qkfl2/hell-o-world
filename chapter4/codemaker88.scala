import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.Path
import scala.collection.JavaConverters
import scala.collection.Iterator

class Chapter4 {
  def solve() {
    var bibimbap = new Bibimbap()
    bibimbap.addMaterial(콩나물.추가, 고추장.추가)
    println(bibimbap.mix())
    
    bibimbap.addMaterial(스팸.추가, 마요네즈.추가, 소고기.추가, 고추장.추가)
    println(bibimbap.mix())
  }
  
  class Bibimbap {
    var 재료들 : Function1[String, String] = null
    
    def addMaterial(materialList : Function1[String, String]*) {
      재료들 = materialList.reduceRight(_ compose _)
    }
    
    def mix() : String = {
      return 재료들.apply("비빔밥");
    }
    
  }
  
  sealed abstract case class 재료(재료명 : String) {
    
    def 추가(재료들 : String) : String = {
      return this.재료명 + " " + 재료들
    }
  }
  
  object 고추장 extends 재료("고추장")
  object 소고기 extends 재료("소고기")
  object 콩나물 extends 재료("콩나물")
  object 스팸 extends 재료("스팸")
  object 참치 extends 재료("참치")
  object 마요네즈 extends 재료("마요네즈")
}