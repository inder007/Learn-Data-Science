package com.example.LearnDataScience.health;

import com.codahale.metrics.health.HealthCheck;

public class TemplateHealthCheck extends HealthCheck {

    private final String template;

    public TemplateHealthCheck(String template){
        this.template = template;
    }

    @Override
    protected Result check() throws Exception {
        //format works as printf in C
        final String saying = String.format(template, "TEST");
        if(!saying.contains("TEST")){
            return Result.unhealthy("template doesn't include name");
        }
        return Result.healthy();
    }
}
