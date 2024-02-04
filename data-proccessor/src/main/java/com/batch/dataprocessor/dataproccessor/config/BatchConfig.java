package com.batch.dataprocessor.dataproccessor.config;

import com.batch.dataprocessor.dataproccessor.listner.JobCompletionNotificationImpl;
import com.batch.dataprocessor.dataproccessor.model.ProductDataDTO;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class BatchConfig {

    @Bean
    public Job jobBean(JobRepository jobRepository, JobCompletionNotificationImpl jobCompletionNotification, Step step) {
        return new JobBuilder("job", jobRepository)
                .listener(jobCompletionNotification)
                .start(step)
                .build();

    }

    @Bean
    public Step steps(JobRepository jobRepository, DataSourceTransactionManager transactionManager,
                      FlatFileItemReader<ProductDataDTO> reader, ItemProcessor<ProductDataDTO, ProductDataDTO> itemProcessor,
                      ItemWriter<ProductDataDTO> itemWriter) {

        return new StepBuilder("jobStep", jobRepository)
                .<ProductDataDTO, ProductDataDTO>chunk(10, transactionManager)
                .reader(reader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();
    }

    @Bean
    public FlatFileItemReader<ProductDataDTO> reader() {
        return new FlatFileItemReaderBuilder<ProductDataDTO>()
                .name("itemReader")
                .resource(new ClassPathResource("data.csv"))
                .delimited().names("productId", "title", "description", "price", "discount")
                .linesToSkip(1)
                .targetType(ProductDataDTO.class)
                .build();
    }

    @Bean
    public ItemProcessor<ProductDataDTO, ProductDataDTO> itemProcessor() {
        return new CustomItemProcessor();
    }

    @Bean
    public ItemWriter<ProductDataDTO> itemWriter(DataSource dataSource) {

        return new JdbcBatchItemWriterBuilder<ProductDataDTO>()
                .sql("insert into products(productId,title,description,price," +
                        "discount,discountPrice)values(:productId, :title, :description, :price, :discount," +
                        " :discountedPrice)")
                .dataSource(dataSource)
                .beanMapped()
                .build();
    }
    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }

}
