package com.nhndooray.samplespringbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SampleSpringBatchApplication {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext ctxt = SpringApplication.run(SampleSpringBatchApplication.class, args);

        // TODO - 02
        // @EnableBatchProcessing
        JobLauncher jobLauncher = ctxt.getBean(JobLauncher.class);
        Job weeklyStatJob = ctxt.getBean("weeklyStatJob", Job.class);
        jobLauncher.run(weeklyStatJob, new JobParametersBuilder().toJobParameters());
    }

}
