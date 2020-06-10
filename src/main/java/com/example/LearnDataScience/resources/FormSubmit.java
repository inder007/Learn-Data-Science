package com.example.LearnDataScience.resources;
import com.example.LearnDataScience.api.CodeRunner;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/formSubmit")
@Produces(MediaType.TEXT_PLAIN)
public class FormSubmit {

    @POST
    public String uploadCode(String code){
        CodeRunner codeRunner = new CodeRunner(code);
        String output = codeRunner.PythonCodeRunner();
        return output;
    }
}
