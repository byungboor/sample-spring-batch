package com.nhndooray.samplespringbatch.stats;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@RequiredArgsConstructor
public class WeeklyStatJobConfig {

    private final JobRepository jobRepository;
    // TODO - 03
    private final JobLauncher jobLauncher;

    @Bean
    public Job weeklyStatJob(Step aggregateWeeklyStatStep) {
        return new JobBuilder("weeklyStatJob", jobRepository)
                .start(aggregateWeeklyStatStep)
                .build();
    }


    // TODO - 04
    @Scheduled(cron = "0 0/10 * * * *")
    public void launchWeeklyStatJob() {
        try {
            System.out.println("Launch WeeklyStatJob --------------------------------------");
            jobLauncher.run(weeklyStatJob(null), new JobParameters());
        } catch (Exception e){
            // Error Handling
        }
    }

}
