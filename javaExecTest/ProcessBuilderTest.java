import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ProcessBuilderTest{
    public static void main(String[] args) {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("/bin/bash", "-c", "sudo docker run --rm sample bash /usr/src/script.sh /usr/src/run.py");
        // processBuilder.command("/bin/bash", "-c", "sudo apt-get -y update");
        try {
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            StringBuilder output = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }
            
            int exitVal = process.waitFor();
            if (exitVal == 0) {
                System.out.println("Success!");
                System.out.println(output);
                System.exit(0);
            }
            else{
                System.out.println("Error");
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}