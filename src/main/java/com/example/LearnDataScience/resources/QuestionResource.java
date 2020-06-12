package com.example.LearnDataScience.resources;

import com.example.LearnDataScience.core.Question;
import com.example.LearnDataScience.db.dao.QuestionDao;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
        this.questionDao.save(question);
        System.out.println(question);
        return Response.ok().build();
    }

    @GET
    @Path("/viewAllQuestions")
    public Response viewAllQuestions(){
        System.out.println("yayy");
        List<Question> questions = this.questionDao.getAllQuestions();
        System.out.println(questions);
        return Response.ok(questions).build();
//        GenericEntity<List<Question>> myEntity = new GenericEntity<List<Question>>(questions) {};
//        return  Response.status(200).entity(myEntity).build();
    }
}
