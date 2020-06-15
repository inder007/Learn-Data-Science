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
//        question.setId(new ObjectId());
//        System.out.println(question);
        boolean flag = this.questionDao.save(question);
//        System.out.println(question);
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
//        return Response.noContent().build();
    }

    @GET
    @Path("/viewAllQuestions")
    public Response viewAllQuestions(){
//        System.out.println("yayy");
        List<Question> questions = this.questionDao.getAllQuestions();
//        System.out.println(questions);
        return Response.ok(questions).build();
//        GenericEntity<List<Question>> myEntity = new GenericEntity<List<Question>>(questions) {};
//        return  Response.status(200).entity(myEntity).build();
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
//        System.out.println(questionId);
        boolean flag = this.questionDao.deleteQuestion(questionId);
//        System.out.println(flag);
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
//        if(flag) return Response.ok().build();
//        return Response.noContent().build();
    }
}
