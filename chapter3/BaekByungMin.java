import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		Files.list(Paths.get("D:\\workspace\\test\\src\\main\\resources")).flatMap(getContents())
				.collect(Collectors.groupingBy(getKey(), Collectors.counting()))
				.entrySet().stream()
				.sorted((c1, c2) -> c2.getValue().compareTo(c1.getValue()))
				.forEach(e -> System.out.println(e));
	}

	private static Function<? super Path, ? extends Stream<? extends String>> getContents() {
		return path -> {
			try {
				return Files.lines(path).collect(Collectors.toList()).stream();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		};
	}

	private static Function<String, ? extends String> getKey() {
		return (String a) -> {
			return a.substring(a.indexOf("@") + 1);
		};
	}

}
