import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by yangs on 2016. 11. 1..
 */
public class main {
    public static void main(String[] args){
        try{
            Map<String,Long> result = Files.list(Paths.get("/tmp/ch3"))
                    .flatMap(Helper::readFile)
                    .collect(Collectors.groupingBy(Helper::getMailDomain , Collectors.counting()));
            result.forEach((k,v)->{
                System.out.println( k + " : " + v);
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }
   static class Helper {
        static Stream<String> readFile(Path path){
            try{
                return Files.lines(path);
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
        static String getMailDomain(String url){
            return url == null ? "" : url.split("@")[1];
        }

    }
}

