package com.example.LearnDataScience.resources;

import java.io.FileOutputStream;
import java.io.IOException;
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
            return code;
        }
        catch(IOException e){
            System.out.println(e);
            return "Error";
        }
    }
}
