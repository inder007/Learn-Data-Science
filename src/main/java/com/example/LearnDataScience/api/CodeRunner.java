package com.example.LearnDataScience.api;

import com.example.LearnDataScience.core.Question;

import java.io.*;
import java.util.concurrent.atomic.AtomicLong;

public class CodeRunner {
    private String code;
    private String fileName;
    private Question question;
    private static AtomicLong atomicLong = new AtomicLong();


    public CodeRunner(Question question, String code) {
        this.code = code;
        this.question = question;
        this.fileName = Long.toString(atomicLong.getAndIncrement());
    }

    private void storeCodeInFile() throws IOException{
        try(FileWriter fileWriter = new FileWriter("codes/" + this.fileName)){
            for(int i=0;i<this.code.length();i++){
                fileWriter.write(this.code.charAt(i));
            }
        }
        catch (IOException e){
            throw new IOException("Error while writing a file");
        }
    }

    private String runDocker() throws IOException, InterruptedException{
        BufferedReader reader = null;
        BufferedReader readerError = null;
        try {
            String command = "docker run -v /Users/joker/Documents/project/Learn-Data-Science/codes/" + this.fileName + ":/usr/src/run.py --rm sample bash /usr/src/script.sh /usr/src/run.py";
            Process process = Runtime.getRuntime().exec(new String[]{"/bin/bash", "-c", command});
            reader = new BufferedReader(new InputStreamReader((process.getInputStream())));
            readerError = new BufferedReader(new InputStreamReader((process.getErrorStream())));
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
//                System.out.println("Error");
//                System.out.println(outError);
                return outError.toString();
            }
            return out.toString();
        }
        catch (IOException e){
            throw new IOException("Exception while running docker");
        }
        catch (InterruptedException e){
            throw new InterruptedException("Interuppt caused while running docker");
        }
        finally {
            if(reader != null){
                reader.close();
            }
            if(readerError != null){
                readerError.close();
            }
        }
    }

    private void deleteCodeFile(){
        File codeFile = new File("./codes/" + this.fileName);
        if ( !codeFile.delete()) {
            System.out.println("Error file not deleted");
        }
    }

    private void addUserCodeToJudgeCode(){
        String judgeCode = this.question.getJudgeCode();
        this.code = judgeCode.replace("#% solution %#", this.code);
        return;
    }

    public String PythonCodeRunner() throws IOException, InterruptedException{
        addUserCodeToJudgeCode();
        storeCodeInFile();
        String output = runDocker();
        deleteCodeFile();

        return output;

    }
}
