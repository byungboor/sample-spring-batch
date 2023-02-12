package com.nhndooray.samplespringbatch.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class FirstTasklet implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        // TODO - 02 : ExecutionContext 를 획득하는 방법
        // TODO - 03 : ExecutionContext 의 자료형
        ExecutionContext jobExecutionContext = contribution.getStepExecution().getJobExecution().getExecutionContext();
        jobExecutionContext.putInt("counter", 1);

        System.out.println("FirstTasklet executed. counter=" + jobExecutionContext.getInt("counter"));
        return RepeatStatus.FINISHED;
    }
}
