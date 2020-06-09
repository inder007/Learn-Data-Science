package com.example.LearnDataScience.api;

import java.io.*;
import java.util.concurrent.atomic.AtomicLong;

public class CodeRunner {
    private String code;
    private String fileName;
    private static AtomicLong atomicLong = new AtomicLong();


    public CodeRunner(String code) {
        this.code = code;
        this.fileName = Long.toString(atomicLong.getAndIncrement());
    }

    private void storeCodeInFile(){
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream("codes/" + this.fileName);
            for (int i = 0; i < this.code.length(); i++) {
                fileOutputStream.write(this.code.charAt(i));
            }
            fileOutputStream.close();
        }
        catch (IOException e){
            System.out.println(e);
        }
    }

    private String runDocker(){
        try {
            String command = "sudo docker run -v /home/joker/project/codes/" + this.fileName + ":/usr/src/run.py --rm sample bash /usr/src/script.sh /usr/src/run.py";
            Process process = Runtime.getRuntime().exec(new String[]{"/bin/bash", "-c", command});
            BufferedReader reader = new BufferedReader(new InputStreamReader((process.getInputStream())));
            BufferedReader readerError = new BufferedReader(new InputStreamReader((process.getErrorStream())));
            String line = "";
            StringBuffer out = new StringBuffer();
            StringBuffer outError = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                out.append(line + "\n");
            }
            while ((line = readerError.readLine()) != null) {
                outError.append(line + "\n");
            }
            int val = process.waitFor();
            if (val != 0) {
                System.out.println("Error");
                System.out.println(outError);
            }
            return out.toString();
        }
        catch (IOException | InterruptedException e){
            System.out.println(e);
        }
        return "Error";
    }

    private void deleteCodeFile(){
        File codeFile = new File("./codes/" + this.fileName);
        if ( !codeFile.delete()) {
            System.out.println("Error file not deleted");
        }
    }

    public String PythonCodeRunner(){
        storeCodeInFile();
        String output = runDocker();
        deleteCodeFile();

        return output;

    }
}
