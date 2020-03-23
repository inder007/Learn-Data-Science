package com.example.LearnDataScience;

import com.example.LearnDataScience.health.TemplateHealthCheck;
import com.example.LearnDataScience.resources.HelloWorldResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class learnDataScienceApplication extends Application<learnDataScienceConfiguration> {

    public static void main(String[] args) throws Exception {
        // main function that is entry point of our application
        new learnDataScienceApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<learnDataScienceConfiguration> bootstrap){
        //To configure the aspects before the application is run.
    }

    @Override
    public void run(learnDataScienceConfiguration learnDataScienceConfiguration, Environment environment) throws Exception {
        final HelloWorldResource resource = new HelloWorldResource(
                learnDataScienceConfiguration.getTemplate(),
                learnDataScienceConfiguration.getDefaultName()
        );

        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(learnDataScienceConfiguration.getTemplate());

        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
    }
}
