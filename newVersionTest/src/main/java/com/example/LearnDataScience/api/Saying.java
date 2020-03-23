package com.example.LearnDataScience.api;


//JSON representation:
//    {
//        "id": 1
//        "content": "Hi!"
//    }


import com.fasterxml.jackson.annotation.JsonProperty;

//immutale and uses jackson to deserialise it
public class Saying {
    private long id;

    private String content;

    public Saying(){
        //jackson deserialization
    }

    public Saying(long id, String content){
        this.id = id;
        this.content = content;
    }

    @JsonProperty
    public long getId(){
        return id;
    }

    @JsonProperty
    public String getContent(){
        return content;
    }

}
