package com.nhndooray.samplespringbatch.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class ThirdTasklet implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        ExecutionContext jobExecutionContext = contribution.getStepExecution().getJobExecution().getExecutionContext();
        jobExecutionContext.putInt("counter", jobExecutionContext.getInt("counter") + 1);

        System.out.println("ThirdTasklet executed. counter=" + jobExecutionContext.getInt("counter"));
        return RepeatStatus.FINISHED;
    }
}
