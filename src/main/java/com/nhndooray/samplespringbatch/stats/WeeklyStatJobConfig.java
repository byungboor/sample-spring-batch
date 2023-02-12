package com.nhndooray.samplespringbatch.stats;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class WeeklyStatJobConfig {

    private final JobRepository jobRepository;

    @Bean
    public Job weeklyStatJob(Step aggregateWeeklyStatStep) {
        return new JobBuilder("weeklyStatJob", jobRepository)
                .start(aggregateWeeklyStatStep)
                .build();
    }

}
