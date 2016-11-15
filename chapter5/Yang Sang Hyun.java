package java8Study;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class main2 {
	private static String BASE_PATH = "D:\\input";
	public static  void main(String[] args) {
		try { 
			Files.list(Paths.get(BASE_PATH))
			.flatMap(Helper::fileRead)
			.filter(Helper::checkStringIsEmptryOrNull)
			.map(Helper::parsingFileString)
			.filter(Helper::checkGirlString)
			.filter(Helper::checkAge)
			.collect(Collectors.groupingBy(result -> result.get("email")))
			.forEach((k,v)->{
				if(v.size() == 1){
					Helper.makeFile(v.get(0).get("userId"), v.get(0).get("name"));
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static class FileWriterArm implements AutoCloseable{
		private  final FileWriter wr;
		public  FileWriterArm(final String filename) throws IOException{
			wr = new FileWriter(BASE_PATH + "\\" + filename);
		}
		public  void writeStuff(final String message) throws IOException{
			wr.write(message);
		}
		@Override
		public void close() throws Exception {
			System.out.println("Close");
			wr.close();
		}
	}

	public static  class Helper {
		public  static  Stream<String> fileRead(Path path) {
			try {
				return Files.lines(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		public static void makeFile(String id , String name){
			try (FileWriterArm file = new FileWriterArm(name + ".txt")){
				file.writeStuff(name + "님 안녕하세요 당첨을 축하합니다!\n");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}


		public static boolean checkGirlString(Map<String,String> map){
			if(map != null && map.containsKey("userId")){
				return map.get("userId").contains("girl");
			}
			return false;
		}

		public static boolean checkAge(Map<String,String> map){
			return getYear(map.get("date")) >= 20;
		}

		public static int getYear(String date) {
			Calendar birth = new GregorianCalendar();
			Calendar today = new GregorianCalendar();
			SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				birth.setTime(smf.parse(date));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			today.setTime(new Date());

			int factor = 0;
			if (today.get(Calendar.DAY_OF_YEAR) < birth.get(Calendar.DAY_OF_YEAR)) {
				factor = -1;
			}
			return today.get(Calendar.YEAR) - birth.get(Calendar.YEAR) + factor;
		}

		public static boolean checkStringIsEmptryOrNull(String text){
			return text == null || text.isEmpty() == false;
		}

		public  static Map<String,String> parsingFileString(String text) {
			Map<String,String> map = new HashMap<>();
			String[] tmp = text.split(" ");

			map.put("name",tmp[0]);
			map.put("userId", tmp[1].split("@")[0]);
			map.put("email", tmp[1]);
			map.put("date", tmp[2]);

			return map;
		}
	}
}
