package com.example.LearnDataScience.db.dao;

import com.example.LearnDataScience.core.Question;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import java.util.ArrayList;
import java.util.List;

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
//                System.out.println(cursor.next().toString());
                questions.add(cursor.next());
            }
        }
        finally {
            cursor.close();
        }
        return questions;
    }

    public void save(Question question){
        this.questionCollection.insertOne(question);
    }


}
