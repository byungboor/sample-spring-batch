package com.nhndooray.samplespringbatch.item;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

// TODO - 01 : JobExecutionListener
public class ItemJobListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("Starting Job : " + jobExecution.getJobInstance().getJobName());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("Ended Job : " + jobExecution.getJobInstance().getJobName());
    }
}
