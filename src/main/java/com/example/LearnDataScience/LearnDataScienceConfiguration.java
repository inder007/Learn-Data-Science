package com.example.LearnDataScience;

import com.example.LearnDataScience.db.MongodbConfiguration;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;


public class LearnDataScienceConfiguration extends Configuration {

    MongodbConfiguration mongodbConfiguration = new MongodbConfiguration();

    @JsonProperty("databaseConfiguration")
    public MongodbConfiguration getMongodbConfiguration() {
        return mongodbConfiguration;
    }

    @JsonProperty("databaseConfiguration")
    public void setMongodbConfiguration(MongodbConfiguration mongodbConfiguration) {
        this.mongodbConfiguration = mongodbConfiguration;
    }
}
