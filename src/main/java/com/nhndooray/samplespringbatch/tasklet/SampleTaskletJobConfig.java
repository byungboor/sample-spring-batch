package com.nhndooray.samplespringbatch.tasklet;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;


@RequiredArgsConstructor
@Configuration
public class SampleTaskletJobConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    // TODO - 01 : FlowBuilder 구성
    @Bean
    public Job sampleTaskletJob() {
        return new JobBuilder("sampleTaskletJob", jobRepository)
                .start(firstStep(null)).on("FAILED").to(secondStep(null)).on("*").end()
                .from(firstStep(null)).on("*").to(thirdStep(null)).on("*").end()
                .end()
                .build();
    }

    @Bean
    public Step firstStep(FirstTasklet firstTasklet) {
        return new StepBuilder("firstStep", jobRepository)
                .tasklet(firstTasklet, transactionManager)
                .build();
    }

    @Bean
    public Step secondStep(SecondTasklet secondTasklet) {
        return new StepBuilder("secondStep", jobRepository)
                .tasklet(secondTasklet, transactionManager)
                .build();
    }

    @Bean
    public Step thirdStep(ThirdTasklet thirdTasklet) {
        return new StepBuilder("thirdStep", jobRepository)
                .tasklet(thirdTasklet, transactionManager)
                .build();
    }
}
