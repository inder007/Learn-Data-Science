package com.example.LearnDataScience.db.dao;

import com.example.LearnDataScience.core.Question;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class QuestionDao {

    private MongoCollection<Question> questionCollection;

    public QuestionDao(MongoCollection<Question> questionCollection) {
        this.questionCollection = questionCollection;
    }

    public List<Question> getAllQuestions(){
        List<Question> questions = new ArrayList<>();
        MongoCursor<Question> cursor = this.questionCollection.find().iterator();
        try{
            while(cursor.hasNext()) {
                questions.add(cursor.next());
            }
        }
        finally {
            cursor.close();
        }
        return questions;
    }

    public boolean save(Question question){
        Question isPresent = this.questionCollection.find(eq("questionId", question.getQuestionId())).first();

        if(isPresent == null){
            this.questionCollection.insertOne(question);
            return true;
        }
        return false;
    }

    public Question getQuestion(String questionId){

        Question question = this.questionCollection.find(eq("questionId", questionId)).first();

        return question;

    }

    public boolean deleteQuestion(String questionId){
        Question question = this.questionCollection.find(eq("questionId", questionId)).first();
        if(question == null){
            return false;
        }

        this.questionCollection.deleteOne(eq("questionId", questionId));
        return true;
    }

    public boolean modifyQuestion(String questionId, Question question){
        Question getQuestion = this.questionCollection.find(eq("questionId", questionId)).first();
        if(getQuestion == null){
            return false;
        }

        this.questionCollection.replaceOne(eq("questionId", questionId), question);
        return true;

    }


}
