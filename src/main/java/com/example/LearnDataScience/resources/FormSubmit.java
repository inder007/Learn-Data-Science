package com.example.LearnDataScience.resources;
import com.example.LearnDataScience.api.CodeRunner;
import com.example.LearnDataScience.core.Question;
import com.example.LearnDataScience.db.dao.QuestionDao;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;


@Path("/formSubmit")
@Produces(MediaType.TEXT_PLAIN)
public class FormSubmit {

    private QuestionDao questionDao;

    public FormSubmit(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @POST
    @Path("/{id}")
    public Response uploadCode(@PathParam("id") String questionId, String code){
        Question question = this.questionDao.getQuestion(questionId);
        CodeRunner codeRunner = new CodeRunner(question, code);
        try {
            String output = codeRunner.PythonCodeRunner();
            return Response.ok(output).build();
        }
        catch (IOException | InterruptedException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
