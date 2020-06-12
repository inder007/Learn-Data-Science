package com.example.LearnDataScience.db;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class MongodbConfiguration {

    @NotEmpty
    private String host;

    @Min(1)
    @Max(65535)
    private int port = 27017;

    @NotEmpty
    private String Database;

    public MongodbConfiguration(){

    }

    @JsonProperty
    public String getHost() {
        return host;
    }

    @JsonProperty
    public String getDatabase() {
        return Database;
    }

    @JsonProperty
    public void setDatabase(String database) {
        Database = database;
    }

    @JsonProperty
    public void setHost(String host) {
        this.host = host;
    }

    @JsonProperty
    public int getPort() {
        return port;
    }

    @JsonProperty
    public void setPort(int port) {
        this.port = port;
    }


    @Override
    public String toString() {
        return "mongodb://"+this.host+":"+this.port;
    }
}
