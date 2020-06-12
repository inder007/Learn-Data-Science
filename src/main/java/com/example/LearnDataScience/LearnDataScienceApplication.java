package com.example.LearnDataScience;

import com.example.LearnDataScience.core.Question;
import com.example.LearnDataScience.db.MongodbConnection;
import com.example.LearnDataScience.db.MongodbManaged;
import com.example.LearnDataScience.db.dao.QuestionDao;
import com.example.LearnDataScience.resources.FormSubmit;
import com.example.LearnDataScience.resources.QuestionResource;
import com.mongodb.client.MongoDatabase;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.forms.MultiPartBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.io.File;

public class LearnDataScienceApplication extends Application<LearnDataScienceConfiguration> {

    public static void main(String[] args) throws Exception {
        new LearnDataScienceApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<LearnDataScienceConfiguration> bootstrap){
        File file = new File("./codes");
        if(!file.isDirectory()){
            boolean flag = new File("./codes").mkdirs();
            System.out.print("Directory created? " + flag);
        }
        bootstrap.addBundle(new AssetsBundle("/assets/dist","/", "index.html"));

        bootstrap.addBundle(new MultiPartBundle());
    }

    @Override
    public void run(LearnDataScienceConfiguration configuration, Environment environment) throws Exception {
        final MongodbConnection connection = new MongodbConnection(configuration.getMongodbConfiguration());

        final MongodbManaged mongodbManaged = new MongodbManaged(connection.getClient());
        environment.lifecycle().manage(mongodbManaged);

        final MongoDatabase database = connection.getClient().getDatabase(configuration.getMongodbConfiguration().getDatabase());

        QuestionDao questionDao = new QuestionDao(database.getCollection("questions", Question.class));
        environment.jersey().register(new FormSubmit());
        environment.jersey().register(new QuestionResource(questionDao));
    }
}
