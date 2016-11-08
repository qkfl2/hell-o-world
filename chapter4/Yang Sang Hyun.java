import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Created by yangs on 2016. 11. 8..
 */
public class Ch4 {
    public static class Material implements Function<Material, Material> {
        public static Material KIMCHI = new Material("김치");
        public static Material GOCHUJANG = new Material("고추장");

        public String materialName;

        public Material(String materialName) {
            this.materialName = materialName;
        }

        @Override
        public String toString(){
            return this.materialName;
        }


        @Override
        public Material apply(Material mate) {
            return new Material(this.materialName + " " + mate.materialName);
        }
    }

    public static class BibimBob{
        private Function<Material, Material> resultMaterial;
        public Material getName(){
            return resultMaterial.apply(new Material("비빔밥"));
        }

        public BibimBob addMaterial(final Function<Material, Material>... materials) {
            resultMaterial = Stream.of(materials)
                    .reduce(Function::compose)
                    .orElse(material -> material);
            return this;
        }
    }

    public static void main(String[] ags) {
        BibimBob bibimBob = new BibimBob();
        bibimBob.addMaterial(Material.GOCHUJANG, Material.KIMCHI);
        System.out.println("Result : " + bibimBob.getName());
    }
}

