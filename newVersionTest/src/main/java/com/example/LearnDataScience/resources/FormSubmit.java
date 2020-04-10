package com.example.LearnDataScience.resources;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("/formSubmit")
@Produces(MediaType.TEXT_HTML)
public class FormSubmit {
    AtomicLong atomicLong = new AtomicLong();
    @POST
    // Look dropwizard-forms tutorial
    public String uploadCode(@FormDataParam("code-area") String code){
        FileOutputStream fout;
//        String filename = new SimpleDateFormat("yyyyMMddHHmm'.txt'").format(new Date());
        String filename = Long.toString(atomicLong.getAndIncrement());
        // System.out.println(code);
        try{
            fout =  new FileOutputStream("codes/" + filename);
            for(int i=0;i<code.length();i++){
                fout.write(code.charAt(i));
                System.out.print(code.charAt(i));
            }
            fout.close();
            String check = "sudo docker run -v /home/joker/project/newVersionTest/codes/"+filename+":/usr/src/run.py --rm sample bash /usr/src/script.sh /usr/src/run.py";
//            String command = "sudo cd /home/joker/project/dockerTest &&  docker run -v /home/joker/project/newVersionTest/codes/"+filename+":/usr/src/run.py --rm -it sample bash /usr/src/script.sh /usr/src/run.py && cd /home/joker/project/newVersionTest";
//            Process proc = Runtime.getRuntime().exec(command);
            Process proc = Runtime.getRuntime().exec(new String[]{"/bin/bash", "-c", check});
            BufferedReader reader = new BufferedReader(new InputStreamReader((proc.getInputStream())));
            BufferedReader readerError = new BufferedReader(new InputStreamReader((proc.getErrorStream())));
            String line = "";
            StringBuffer out = new StringBuffer();
            StringBuffer outError = new StringBuffer();
            while((line = reader.readLine()) != null){
//                System.out.print(line + "\n");
                out.append(line);
            }
            while((line = readerError.readLine()) != null){
//                System.out.print(line + "\n");
                outError.append(line);
            }
            int val = proc.waitFor();
//            System.out.println(val);
            if(val == 0){
                System.out.println("Success!!");
            }
            else{
                System.out.println("Error");
                System.out.println(outError);
            }
            File temp = new File("./codes/"+filename);
            if(temp.delete()){
                System.out.println(temp.getName() + " deleted");
            }
            else{
                System.out.println("Error file not deleted");
            }
            return out.toString();
        }
        catch(IOException | InterruptedException e){
            System.out.println(e);
            return "Error";
        }
    }
}
