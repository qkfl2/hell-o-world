package chapter4;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Created by bross on 2016. 11. 1..
 */
public class Chapter4 {
    static class Bibimbap extends Material {
        private Function<Material, Material> materialFunctions;

        public Bibimbap() {
            name = "비빔밥";
            addMaterial();
        }

        public void addMaterial(final Function<Material, Material>... materials) {
            materialFunctions = Stream.of(materials).reduce((materialMaterialFunction, next) -> materialMaterialFunction.compose(next)).orElseGet(Function::identity);

        }

        public Material mix() {
            Stream.of(materialFunctions).forEach(materialFunctions ->{
                name = materialFunctions.apply(this).toString();
            });
            return this;
        }

    }

    static class Material {
        protected String name;

        protected Material() {

        }

        private Material(String material) {
            this.name = material;
        }

        public Material beanSprouts() {
            return new Material(toString() + " 콩나물");
        }

        public Material spam() {
            return new Material(toString() + " 스팸");
        }

        public Material gochujang() {
            return new Material(toString() + " 고추장");
        }

        @Override
        public String toString() {
            return name + " ";
        }

    }

    public static void main(String[] ags) {
        Bibimbap bibimbap = new Bibimbap();
        bibimbap.addMaterial(Material::beanSprouts, Material::spam, Material::gochujang);
        System.out.println(bibimbap.mix());

    }
}
