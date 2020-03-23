package com.example.LearnDataScience;

import com.fasterxml.jackson.annotation.JsonProperty;

//import io;
import io.dropwizard.Configuration;

import javax.validation.constraints.NotEmpty;

public class learnDataScienceConfiguration extends Configuration {
    @NotEmpty
    private String template;

    @NotEmpty
    private String defaultName = "Stranger";

    @JsonProperty
    public String getTemplate(){
        return template;
    }

    @JsonProperty
    public void setTemplate(String template){
        this.template = template;
    }

    @JsonProperty
    public  String getDefaultName(){
        return defaultName;
    }

    @JsonProperty
    public void setDefaultName(String defaultName){
        this.defaultName = defaultName;
    }

}
