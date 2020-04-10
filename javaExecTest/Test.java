import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

// import java.io

// https://mkyong.com/java/how-to-execute-shell-command-from-java/

public class Test{
    private void checkTest(){
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

    public static void main(String[] args) {
        // checkTest();
        

        try {
            File file = new File("./codes");
            // file.createNewFile();
            if(file.isDirectory()){
                System.out.println("File is a directory");
            }
            else{
                boolean flag = new File("./codes").mkdirs();
                // boolean flag = file.mkdir();
                System.out.print("Directory created? " + flag);
            }
            
        } catch (Exception e) {
            //TODO: handle exception
        }
        
    }
}