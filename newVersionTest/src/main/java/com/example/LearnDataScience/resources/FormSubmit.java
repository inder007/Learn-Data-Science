package com.example.LearnDataScience.resources;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("/formSubmit")
@Produces(MediaType.TEXT_HTML)
public class FormSubmit {
    @POST
    // Look dropwizard-forms tutorial
    public String uploadCode(@FormDataParam("code-area") String code){
        FileOutputStream fout;
        String filename = new SimpleDateFormat("yyyyMMddHHmm'.txt'").format(new Date());
        // System.out.println(code);
        try{
            fout =  new FileOutputStream("codes/" + filename);
            for(int i=0;i<code.length();i++){
                fout.write(code.charAt(i));
                System.out.print(code.charAt(i));
            }
            fout.close();
            String check = "docker run -v /home/joker/project/newVersionTest/codes/"+filename+":/usr/src/run.py --rm -it sample bash /usr/src/script.sh /usr/src/run.py";
//            String command = "sudo cd /home/joker/project/dockerTest &&  docker run -v /home/joker/project/newVersionTest/codes/"+filename+":/usr/src/run.py --rm -it sample bash /usr/src/script.sh /usr/src/run.py && cd /home/joker/project/newVersionTest";
//            String command = "sudo ls ; ls";
//            Process proc = Runtime.getRuntime().exec(command);
            Process proc = Runtime.getRuntime().exec(new String[]{"su", "-c", check});
            BufferedReader reader = new BufferedReader(new InputStreamReader((proc.getInputStream())));
            String line = "";
            StringBuffer out = new StringBuffer();
            while((line = reader.readLine()) != null){
//                System.out.print(line + "\n");
                out.append(line);
            }
            int val = proc.waitFor();
            System.out.println(val);
            return out.toString();
        }
        catch(IOException | InterruptedException e){
            System.out.println(e);
            return "Error";
        }
    }
}
