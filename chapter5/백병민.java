import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {
	public static void main(String[] args) throws IOException {
		Files.list(Paths.get("D:\\a"))
				.flatMap(path -> 파일에서내용읽기(path))
				.filter(line -> 이메일과나이검사(line)) // girl 포함 여부, 나이 검사
				.collect(Collectors.groupingBy(line -> line.split(" ")[1], Collectors.toList())) // 중복 검사를 위해 그룹핑 
				.entrySet()
				.stream()
				.filter(e -> 중복메일검사(e))
				.forEach(e -> 결과를파일에출력(e));
	}

	private static boolean 중복메일검사(Entry<String, List<String>> e) {
		return e.getValue().size() == 1;
	}

	private static void 결과를파일에출력(Entry<String, List<String>> e) {
		try {
			final String[] data = e.getValue().get(0).split(" ");
			final String id = data[1].substring(0, data[1].indexOf('@'));
			final String content = String.format("==메세지 내용== %s님 안녕하세요 당첨을 축하합니다!", data[0]);
			Files.write(Paths.get(id + ".txt"), content.getBytes(StandardCharsets.UTF_8), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private static boolean 이메일과나이검사(String line) {
		if (line.equals("") == false) {
			final String[] data = line.split(" ");
			if (data.length == 3 && data[1].contains("girl") && Integer.parseInt(data[2].substring(0, 4)) < 1996 == true) {
				return true;
			}
		}
		return false;
	}

	private static Stream<String> 파일에서내용읽기(Path path) {
		try {
			return Files.lines(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
