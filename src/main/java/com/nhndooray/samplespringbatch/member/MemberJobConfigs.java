package com.nhndooray.samplespringbatch.member;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nhndooray.samplespringbatch.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;


// TODO-03
@Slf4j
@Configuration
public class MemberJobConfigs {


    private final JobRepository jobRepository;

    public MemberJobConfigs(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    // TODO-04 : https://renuevo.github.io/spring/batch/spring-batch-chapter-2/
    @Bean
    public Job dummyJob(JobRepository jobRepository) {
        return new JobBuilder("dummyJob", jobRepository)
                .preventRestart() // disable restartability
                .start(initDummyStep()).build();
    }

    @Bean
    public Step initDummyStep() {
        return new StepBuilder("initDummyStep", jobRepository)
                .chunk(3)
                .reader(memberFileReader())
                .processor(memberProcessor())
                .writer(memberFileWriter())
                .build();
    }

    @Bean
    public ItemReader<Member> memberFileReader() {
        FlatFileItemReader<Member> memberFileReader = new FlatFileItemReader<>();
        memberFileReader.setResource(new ClassPathResource("/member/member.txt"));
        memberFileReader.setLineMapper(memberMapper);
        return memberFileReader;
    }

    @Bean
    public ItemProcessor memberProcessor() {
        return new MemberProcessor();
    }

    @Bean
    public ItemWriter<Member> memberFileWriter() {
        FlatFileItemWriter<Member> writer = new FlatFileItemWriter<>();
        writer.setName("memberJsonWriter");
        writer.setResource(new FileSystemResource("~/output/member.json"));
        writer.setLineAggregator(memberAggregator);
        return writer;
    }

    LineMapper<Member> memberMapper = (line, lineNumber) -> {
        try {
            return new Member(line);
        } catch (Exception e) {
            log.error("error processing line:{} = {},e", lineNumber, line, e);
            throw e;
        }
    };

    LineAggregator<Member> memberAggregator = (member) -> {
        try {
            return JsonUtil.OBJECT_MAPPER.writeValueAsString(member);
        } catch (JsonProcessingException e) {
            log.error("error processing. member:{}", member, e);
            throw new RuntimeException(e);
        }
    };
}
