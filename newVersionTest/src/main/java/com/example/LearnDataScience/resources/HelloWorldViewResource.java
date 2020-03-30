package com.example.LearnDataScience.resources;

import com.codahale.metrics.annotation.Timed;
import com.example.LearnDataScience.views.HelloWorldView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("views")
@Produces(MediaType.TEXT_HTML)
public class HelloWorldViewResource {

    @GET
    public HelloWorldView getView(){
        return new HelloWorldView("helloWorld.ftl");
    }
}
