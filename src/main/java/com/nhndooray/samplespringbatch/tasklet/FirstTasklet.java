package com.nhndooray.samplespringbatch.tasklet;

import org.springframework.batch.core.ExitStatus;
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

        ExecutionContext jobExecutionContext = contribution.getStepExecution().getJobExecution().getExecutionContext();
        jobExecutionContext.putInt("counter", 1);

        System.out.println("FirstTasklet executed. counter=" + jobExecutionContext.getInt("counter"));

        // TODO 02 - exitStatus 에 상태에 따라서 조건 분기
        contribution.setExitStatus(ExitStatus.FAILED);

        return RepeatStatus.FINISHED;
    }
}
