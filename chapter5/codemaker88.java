package Chapter5;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by codemaker88 on 2016-11-13.
 */
public class Chapter5 {
    public static void main(String[] args) {
        try {
            Map<String, List<Chapter5Helper.EventData>> result = Files.list(Paths.get("./input/Chapter5"))
                    .flatMap(Chapter5Helper::readData)
                    .map(Chapter5Helper::parsingData)
                    .filter(Chapter5Helper::winnerFilter)
                    .collect(Collectors.groupingBy(data -> data.email));

            result.forEach(Chapter5Helper::writeResult);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class Chapter5Helper {

        private static DateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        private static int MIN_AGE = 20;
        private static String WINNER_MESSAGE = "%s님 안녕하세요 당첨을 축하합니다!";

        static class EventData {
            String name;
            String email;
            String birthday;

            public EventData(String name, String email, String birthday) {
                this.name = name;
                this.email = email;
                this.birthday = birthday;
            }

            @Override
            public String toString() {
                return name + ", " + email + "," + birthday;
            }
        }

        static class WriterARM implements AutoCloseable {

            private final FileWriter writer;

            public WriterARM(final String fileName) throws IOException {
                writer = new FileWriter(fileName);
            }

            public void write(String message) throws IOException {
                writer.write(message);
            }

            @Override
            public void close() throws Exception {
                writer.close();
            }
        }

        static Stream<String> readData(Path path) {
            try {
                return Files.lines(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        static EventData parsingData(String lineData) {
            if (!lineData.isEmpty()) {
                String[] split = lineData.split(" ");
                if (split.length >= 3) {
                    return new EventData(split[0], split[1], split[2]);
                }
            }
            return null;
        }

        static boolean winnerFilter(EventData data) {
            if (data != null) {
                return data.email.contains("girl") && checkBirthday(data.birthday);
            }
            return false;
        }

        static boolean checkBirthday(String birthdayString) {

            Calendar calendar = Calendar.getInstance();
            int currentYear = calendar.get(Calendar.YEAR);
            int birthdayYear = currentYear;

            try {
                calendar.setTime(sDateFormat.parse(birthdayString));
                birthdayYear = calendar.get(Calendar.YEAR);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            return currentYear - birthdayYear > MIN_AGE;
        }

        static void writeResult(String email, List<EventData> dataList) {

            //중복은 제외 대상
            if (dataList.size() == 1) {
                String id = dataList.get(0).email.substring(0, dataList.get(0).email.lastIndexOf('@'));
                try(final WriterARM writer = new WriterARM(id + ".txt")) {
                    writer.write(String.format(WINNER_MESSAGE, dataList.get(0).name));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
