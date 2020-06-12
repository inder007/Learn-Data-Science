package com.example.LearnDataScience.db;

import com.mongodb.client.MongoClient;
import io.dropwizard.lifecycle.Managed;

public class MongodbManaged implements Managed {

    private MongoClient mongoClient;

    public MongodbManaged(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Override
    public void start() throws Exception {

    }

    @Override
    public void stop() throws Exception {
        this.mongoClient.close();
    }
}
