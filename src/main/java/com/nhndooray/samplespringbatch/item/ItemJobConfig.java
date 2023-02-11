package com.nhndooray.samplespringbatch.item;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nhndooray.samplespringbatch.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
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
import org.springframework.transaction.PlatformTransactionManager;


@Slf4j
@Configuration
@RequiredArgsConstructor
public class ItemJobConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    // TODO-02
    //  - JobBuilder 를 사용하여
    //  - job 이름이 itemClickThroughRateJob 이고
    //  - Step itemClickStep 스프링 빈을 포함하는 Job 을 만드세요
    @Bean
    public Job itemClickThroughRateJob() {
        return null;
    }

    // TODO-03
    //  - StepBuilder 를 사용하여
    //  - Step 이름이 itemClickStep 이고
    //  - Chunk 크기가 3 이고
    //  - accessLogReader(), clickLogFilterProcess(), itemClickWriter() 를 포함하는 Step 을 만드세요
    @Bean
    public Step itemClickStep() {
        return null;
    }

    @Bean
    public ItemReader<AccessLog> accessLogReader() {
        FlatFileItemReader<AccessLog> accessLogFileReader = new FlatFileItemReader<>();
        accessLogFileReader.setResource(new ClassPathResource("/item/access.log"));
        accessLogFileReader.setLineMapper(accessLogLineMapper);
        return accessLogFileReader;
    }

    LineMapper<AccessLog> accessLogLineMapper = (line, lineNumber) -> {
        try {
            System.out.println("read line : " + line);
            return new AccessLog(line);
        } catch (Exception e) {
            log.error("error processing line:{} = {},e", lineNumber, line, e);
            throw e;
        }
    };

    @Bean
    public ItemProcessor clickLogFilterProcessor() {
        return new ClickLogFilterProcessor();
    }

    @Bean
    public ItemWriter<ItemClickLog> itemClickLogWriter() {

        FlatFileItemWriter<ItemClickLog> writer = new FlatFileItemWriter<>();
        writer.setName("itemClickLogWriter");
        writer.setResource(new FileSystemResource("./output/clickLog.json"));
        writer.setLineAggregator(memberAggregator);
        return writer;
    }

    LineAggregator<ItemClickLog> memberAggregator = (itemClickLog) -> {
        try {
            System.out.println("write item : " + itemClickLog.getItemId());
            return JsonUtil.OBJECT_MAPPER.writeValueAsString(itemClickLog);
        } catch (JsonProcessingException e) {
            log.error("error processing. itemClickLog:{}", itemClickLog, e);
            throw new RuntimeException(e);
        }
    };
}
