import java.util.function.Function;
import java.util.stream.Stream;

public class Main {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		System.out.println(new Bibimbap().addMaterial(Material.사과, Material.참외).mix());
	}

	private static class Material implements Function<Material, Material> {
		public static Material 참외 = new Material("참외");
		public static Material 사과 = new Material("사과");

		public final String name;

		public Material(String name) {
			this.name = name;
		}

		@Override
		public Material apply(Material t) {
			return new Material(this.name + " " + t.name);
		}

		@Override
		public String toString() {
			return this.name;
		}
	}

	@SuppressWarnings("unchecked")
	private static class Bibimbap {
		private Function<Material, Material> material;

		public Bibimbap() {
			addMaterial();
		}

		public Material mix() {
			return material.apply(new Material("비빔밥"));
		}

		public Bibimbap addMaterial(final Function<Material, Material>... materials) {
			material = Stream.of(materials).reduce(Function::compose).orElse(material -> material);
			return this;
		}
	}
}
