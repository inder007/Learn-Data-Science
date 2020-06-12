package com.example.LearnDataScience.db;

import com.example.LearnDataScience.core.Question;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class QuestionDatabase {

    public static void main(String[] args) {
//        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
//        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);

        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017");

//        MongoClientSettings clientSettings = MongoClientSettings.builder()
//                .applyConnectionString(connectionString)
//                .codecRegistry(codecRegistry)
//                .build();
//        MongoClient mongoClient = MongoClients.create(clientSettings);

        MongoClient mongoClient = MongoClients.create();


//        MongoDatabase database = mongoClient.getDatabase("test1");
        MongoDatabase database = mongoClient.getDatabase("test1").withCodecRegistry(codecRegistry);

        MongoCollection<Question> collection = database.getCollection("questions", Question.class);

//        Document doc = new Document("name", "question 1")
//                        .append("question", "Print Hii!")
//                        .append("solution", "print('Hi')");

        Question question = new Question("Print Hii!", "print(hii)");

        collection.insertOne(question);
        System.out.println(question);

        question = new Question("Print Hii!", "print(hii)");
        System.out.println(question.getId());

        collection.insertOne(question);


        MongoCursor<Question> cursor = collection.find().iterator();

        try{
            while(cursor.hasNext()) {
                System.out.println(cursor.next().toString());
            }
        }
        finally {
            cursor.close();
        }

//        Document myDoc = collection.find().first();
//        System.out.println(myDoc.toJson());
        Question question1 = collection.find().first();

        collection.deleteMany(eq("question", "Print Hii!"));

        cursor = collection.find().iterator();

        try{
            while(cursor.hasNext()) {
                System.out.println(cursor.next().toString());
            }
        }
        finally {
            cursor.close();
        }

    }


}
