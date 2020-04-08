package main.java.com.example.LearnDataScience.resources;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/formSubmit")
@Produces(MediaType.APPLICATION_JSON)
public class FormSubmit {

    @POST
    // Look dropwizard-forms tutorial
    public String uploadCode(){

        return "";
    }
}
