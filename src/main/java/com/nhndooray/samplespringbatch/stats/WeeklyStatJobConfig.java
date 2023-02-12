package com.nhndooray.samplespringbatch.stats;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@Configuration
@RequiredArgsConstructor
public class WeeklyStatJobConfig {

    private final JobRepository jobRepository;
    private final JobLauncher jobLauncher;

    @Bean
    public Job weeklyStatJob(Step aggregateWeeklyStatStep) {

        // TODO - 03
        //   - WeeklyStatJobValidator 를 weeklyStatJob 에 설정합니다.

        return new JobBuilder("weeklyStatJob", jobRepository)
                .start(aggregateWeeklyStatStep)
                .build();
    }

    @Scheduled(cron = "0/10 * * * * *")
    public void launchWeeklyStatJob() {
        try {
            System.out.println("Launch WeeklyStatJob --------------------------------------");

            // TODO 01
            //   - JobParameters 객체를 생성하고 객체에 다음 값을 넣습니다.
            //   - 이름이 beginDate 이고, 값은 문자열 20230101
            //   - 이름이 endDate 이고, 값은 문자열 20230107
            //   - 이름이 version 이고, 값은 문자열 LocalDateTime.now().toString() : JobParameter 값이 중복되어 Job 이 실패하는 것을 방지
            JobParameters parameters = null;
            jobLauncher.run(weeklyStatJob(null), parameters);
        } catch (Exception e) {
            e.printStackTrace();
            // Error Handling
        }
    }

}
