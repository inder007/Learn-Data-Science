package com.example.LearnDataScience.resources;
import com.example.LearnDataScience.api.CodeRunner;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;


@Path("/formSubmit")
@Produces(MediaType.TEXT_PLAIN)
public class FormSubmit {

    @POST
    public Response uploadCode(String code){
        CodeRunner codeRunner = new CodeRunner(code);
        try {
            String output = codeRunner.PythonCodeRunner();
            return Response.ok(output.toString()).build();
        }
        catch (IOException | InterruptedException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
