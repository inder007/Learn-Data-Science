package com.example.LearnDataScience.resources;

import com.example.LearnDataScience.api.CodeRunner;
import com.example.LearnDataScience.core.Question;
import com.example.LearnDataScience.db.dao.QuestionDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

@Path("/question")
@Produces(MediaType.APPLICATION_JSON)
public class QuestionResource {

    private QuestionDao questionDao;

    public QuestionResource(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @POST
    @Path("/addQuestion")
    public Response addQuestion(Question question){
        boolean flag = this.questionDao.save(question);
        if(flag){
            CodeRunner codeRunner = new CodeRunner(question, question.getSolution());
            try{
                String questionStatus = codeRunner.PythonCodeRunner();
                return Response.ok(questionStatus).build();
            }
            catch(IOException | InterruptedException e) {
                return Response.ok("Question added but code couldn't be verified!").build();
            }
        }
        return Response.ok("Question Id already present in database. Please change the Question Id").build();
    }

    @GET
    @Path("/viewAllQuestions")
    public Response viewAllQuestions(){
        List<Question> questions = this.questionDao.getAllQuestions();
        return Response.ok(questions).build();
    }

    @GET
    @Path("/{id}")
    public Response getQuestion(@PathParam("id") String questionId){
        Question question = this.questionDao.getQuestion(questionId);
        return Response.ok(question).build();
    }

    @POST
    @Path("/deleteQuestion")
    public Response deleteQuestion(String questionId){
        boolean flag = this.questionDao.deleteQuestion(questionId);
        if(flag) return Response.ok("Question successfully deleted").build();
        return Response.ok("Question not present in database").build();
    }

    @POST
    @Path("/modifyQuestion/{id}")
    public Response updateQuestion(@PathParam("id") String questionId, Question question){
        boolean flag = this.questionDao.modifyQuestion(questionId, question);
        if(flag){
            CodeRunner codeRunner = new CodeRunner(question, question.getSolution());
            try{
                String questionStatus = codeRunner.PythonCodeRunner();
                return Response.ok(questionStatus).build();
            }
            catch(IOException | InterruptedException e) {
                return Response.ok("Question added but code couldn't be verified!").build();
            }
        }
        return Response.ok("No Question with this Question Id found, try adding the question").build();
    }
}
