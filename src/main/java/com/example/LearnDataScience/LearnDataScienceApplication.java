package com.example.LearnDataScience;

import com.example.LearnDataScience.resources.FormSubmit;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;   
import io.dropwizard.setup.Environment;
import io.dropwizard.forms.MultiPartBundle;

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
    public void run(LearnDataScienceConfiguration learnDataScienceConfiguration, Environment environment) throws Exception {
        environment.jersey().register(new FormSubmit());
    }
}
