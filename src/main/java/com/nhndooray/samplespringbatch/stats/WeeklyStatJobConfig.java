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

    // TODO - 04
    //   - Step 빈 설정이 복잡해지면 이처럼 WeeklyStatJobConfig 과 WeeklyStatStepConfig 으로 분리해도 된다.
    @Bean
    public Job weeklyStatJob(Step aggregateWeeklyStatStep) {
        return new JobBuilder("weeklyStatJob", jobRepository)
                .start(aggregateWeeklyStatStep)
                .build();
    }

}
