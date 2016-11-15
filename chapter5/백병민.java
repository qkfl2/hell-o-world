import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class 백병민 {
	public static void main(String[] args) throws IOException {
		
		Files.list(Paths.get("D:\\a"))
				.flatMap(백병민::파일에서_내용읽기)
				.filter(백병민::빈_줄이_아닌가)
				.map(백병민::이름_메일_생년월일_배열로_변경)
				.filter(백병민::이메일에_girl이_포함되었는가)
				.filter(백병민::나이가_20세_이상인가)
				.collect(동일한_이메일끼리_묶기()).entrySet().stream()
				.filter(백병민::동일한_이메일이_없는가)
				.forEach(백병민::파일에_당첨자_기록);
	}

	private static Collector<String[], ?, Map<String, List<String[]>>> 동일한_이메일끼리_묶기() {
		return Collectors.groupingBy(array -> array[1], Collectors.toList());
	}

	private static void 파일에_당첨자_기록(Entry<String, List<String[]>> e) {
		try {
			String id = e.getValue().get(0)[1].substring(0, e.getValue().get(0)[1].indexOf('@'));
			String contents = "==메세지 내용== "+e.getValue().get(0)[0]+"님 안녕하세요 당첨을 축하합니다!";
			Files.write(Paths.get(id + ".txt"), contents.getBytes(StandardCharsets.UTF_8), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private static boolean 동일한_이메일이_없는가(Entry<String, List<String[]>> e) {
		return e.getValue().size() == 1;
	}

	private static boolean 나이가_20세_이상인가(String[] array) {
		return Integer.parseInt(array[2].substring(0, 4)) < 1996;
	}

	private static boolean 이메일에_girl이_포함되었는가(String[] array) {
		return array[1].toLowerCase().contains("girl");
	}

	private static String[] 이름_메일_생년월일_배열로_변경(String line) {
		return line.split(" ");
	}

	private static boolean 빈_줄이_아닌가(String line) {
		return line.length() != 0;
	}

	private static Stream<String> 파일에서_내용읽기(Path path) {
		try {
			return Files.lines(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
