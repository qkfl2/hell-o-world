class Bibimbop {
  var material: Function1[Material, Material] = null
  def addMaterial(materials: Function1[Material, Material]*) = material = materials.reduce(_ compose _)
  def mix(): Material = material.apply(new Material("비빔밥"))
}

class Material(nameParam: String) extends Function1[Material, Material] {
  var name: String = nameParam
  def apply(t: Material) = new Material(this.name + " " + t.name)
  override def toString(): String = name
}

object Test {
  def main(args: Array[String]) {
    var bibimbop = new Bibimbop
    bibimbop.addMaterial(new Material("참외"), new Material("사과"))
    printf(bibimbop.mix().toString())
  }
}
