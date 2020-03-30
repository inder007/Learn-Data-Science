package com.example.LearnDataScience.resources;


import com.codahale.metrics.annotation.Timed;
import com.example.LearnDataScience.api.Saying;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Path("hello-world") //tells jersey that this resource accessible at this uri
@Produces(MediaType.APPLICATION_JSON) //lets jersey know that this resource produces representations which are application/json
public class HelloWorldResource {
    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public HelloWorldResource(String template, String defaultName){
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong(); //way to generate thread-safe unique id
    }

    @GET
    @Timed //records the duration and rate of its invocations as a Metrics Timer.
    public Saying sayHello(@QueryParam("name") Optional<String> name){
        final String value = String.format(template, name.orElse(defaultName));
        return new Saying(counter.incrementAndGet(), value);
    }

}
