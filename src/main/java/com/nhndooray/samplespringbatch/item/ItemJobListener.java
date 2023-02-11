package com.nhndooray.samplespringbatch.item;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.annotation.AfterJob;
import org.springframework.batch.core.annotation.BeforeJob;
import org.springframework.stereotype.Component;

// TODO - 01
@Component
public class ItemJobListener {
    @BeforeJob
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("Starting Job : " + jobExecution.getJobInstance().getJobName());
    }
    @AfterJob
    public void afterJob(JobExecution jobExecution) {
        System.out.println("Ended Job : " + jobExecution.getJobInstance().getJobName());

        if (ExitStatus.COMPLETED != jobExecution.getExitStatus())
            System.out.println("Alarm! send TEXT : " + jobExecution.getExitStatus());
    }

}
