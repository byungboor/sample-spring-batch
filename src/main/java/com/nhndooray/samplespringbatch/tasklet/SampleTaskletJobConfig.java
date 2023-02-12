package com.nhndooray.samplespringbatch.tasklet;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.config.Task;
import org.springframework.transaction.PlatformTransactionManager;

// TODO - 01 : 구성
@RequiredArgsConstructor
@Configuration
public class SampleTaskletJobConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Bean
    public Job sampleTaskletJob(){
        return new JobBuilder("sampleTaskletJob", jobRepository)
                .start(firstStep(null))
                .next(secondStep(null))
                .next(thirdStep(null))
                .build();
    }

    @Bean
    public Step firstStep(FirstTasklet firstTasklet){
        return new StepBuilder("firstStep", jobRepository)
                .tasklet(firstTasklet, transactionManager)
                .build();
    }

    @Bean
    public Step secondStep(SecondTasklet secondTasklet){
        return new StepBuilder("secondStep", jobRepository)
                .tasklet(secondTasklet, transactionManager)
                .build();
    }

    @Bean
    public Step thirdStep(ThirdTasklet thirdTasklet){
        return new StepBuilder("thirdStep", jobRepository)
                .tasklet(thirdTasklet, transactionManager)
                .build();
    }
}
