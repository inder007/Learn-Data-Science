import java.io.BufferedReader;
import java.io.InputStreamReader;

// import java.io

// https://mkyong.com/java/how-to-execute-shell-command-from-java/

public class Test{
    public static void main(String[] args) {
        try {
            String command = "ping google.com";
            Process proc = Runtime.getRuntime().exec(command);
            // StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = "";
            while((line = reader.readLine()) != null){
                System.out.print(line + "\n");
            }

            proc.waitFor();
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        
    }
}