package com.nhndooray.samplespringbatch.stats;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class WeeklyStatStepConfig {

    private final DataSource dataSource;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    private static final Integer PAGE_SIZE = 3;
    private static final Integer CHUNK_SIZE = 3;

    @Bean
    public Step aggregateWeeklyStatStep() throws Exception {

        // TODO - 08
        //    - 적절한 reader processor writer 를 설정합니다.

        return new StepBuilder("aggregateWeeklyStatStep", jobRepository)
                .chunk(CHUNK_SIZE, transactionManager)
                .transactionManager(transactionManager)
                .build();
    }

    @Bean
    public JdbcPagingItemReader<WeeklyStat> aggregatedWeeklyStatReader() throws Exception {
        Map<String, Object> paramValues = Map.of(
                "beginDate", "20230101",
                "endDate", "20230107");

        // TODO - 05
        //   - JdbcPagingItemReaderBuilder 객체에 queryProvider() 와 parameterValues() 메서드를 사용하여
        //   - 적절한 queryProvider 와 parameterValue 값을 설정합니다.

        return new JdbcPagingItemReaderBuilder<WeeklyStat>()
                .pageSize(PAGE_SIZE)
                .fetchSize(PAGE_SIZE)
                .dataSource(dataSource)
                .rowMapper(new BeanPropertyRowMapper<>(WeeklyStat.class))
                .name("aggregatedWeeklyStatReader")
                .build();
    }

    // TODO - 06
    //  - Background 의 집계 쿼리를 참고하여 PagingQueryProvider 스프링 빈을 만들어주세요.
    //  - 조건의 where stat_date >= ? and stat_date <= ? 는 변수 처리해야 합니다.
    //  - 변수는 aggregatedWeeklyStatReader() 의 paramValues 의 이름을 참고하세요.
    @Bean
    public PagingQueryProvider createQueryProvider() throws Exception {
        var query = new SqlPagingQueryProviderFactoryBean();
        query.setDataSource(dataSource);
        query.setSelectClause("min(stat_date) as stat_date, item_id, sum(click_count) as click_count, sum(expose_count) as expose_count ");
        query.setFromClause("from STAT_DAILY");
        query.setWhereClause("where stat_date >= ? and stat_date <= ?");
        query.setGroupClause("group by stat_date item_id");
        query.setSortKeys(Map.of("stat_date", Order.ASCENDING, "item_id", Order.ASCENDING));

        return query.getObject();
    }

    @Bean
    public ItemProcessor ctrProcessor() {
        return new CtrProcessor();
    }

    // TODO - 07
    //  - beanMapped() 방식을 사용하여 STAT_WEEKLY 테이블에 데이터를 저장하는 weeklyStatWriter 스프링 빈을 만들어주세요
    @Bean
    public ItemWriter<WeeklyStat> weeklyStatWriter() {
        return null;
    }

}
