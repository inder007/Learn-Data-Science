package com.example.LearnDataScience;

import com.example.LearnDataScience.health.TemplateHealthCheck;
import com.example.LearnDataScience.resources.HelloWorldResource;
import com.example.LearnDataScience.resources.HelloWorldViewResource;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;   
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

import java.util.Map;

public class learnDataScienceApplication extends Application<learnDataScienceConfiguration> {

    public static void main(String[] args) throws Exception {
        // main function that is entry point of our application
        new learnDataScienceApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<learnDataScienceConfiguration> bootstrap){
        //To configure the aspects before the application is run.
        bootstrap.addBundle(new AssetsBundle("/assets/","/"));
        bootstrap.addBundle(new ViewBundle<learnDataScienceConfiguration>(){
            @Override
            public Map<String, Map<String, String>> getViewConfiguration(learnDataScienceConfiguration configuration) {
                return configuration.getViewRendererConfiguration();
            }
        });
//        bootstrap.addBundle(new ViewBundle<learnDataScienceConfiguration>());
    }

    @Override
    public void run(learnDataScienceConfiguration learnDataScienceConfiguration, Environment environment) throws Exception {
        final HelloWorldResource resource = new HelloWorldResource(
                learnDataScienceConfiguration.getTemplate(),
                learnDataScienceConfiguration.getDefaultName()
        );

        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(learnDataScienceConfiguration.getTemplate());

//        final HelloWorldViewResource viewResource1 = new HelloWorldViewResource();

        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
        environment.jersey().register(new HelloWorldViewResource());
    }
}
