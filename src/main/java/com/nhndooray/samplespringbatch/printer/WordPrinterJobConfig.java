package com.nhndooray.samplespringbatch.printer;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.NoWorkFoundStepExecutionListener;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class WordPrinterJobConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Bean
    public Job wordPrinterJob() {
        return new JobBuilder("wordPrinterJob", jobRepository)
                .start(wordPrintStep(null))
                .build();
    }

    @Bean
    public Step wordPrintStep(Tasklet wordPrinterTasklet) {
        // TODO -05
        //    - StepBuilder 와 tasklet() 메서드를 사용하여 Tasklet 을 포함하는 wordPrintStep 스프링 빈을 만들어보자.
        return new StepBuilder("wordPrintStep", jobRepository)
                .tasklet(wordPrinterTasklet, transactionManager)
                .build();
    }

}
