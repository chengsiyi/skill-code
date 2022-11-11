package com.chengsy.code.tools.moxi.mongo.bean;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;

/**
 * @author chengsiyi
 * @date 2022/10/2 21:04
 */
@Component
@Configuration
@EnableMongoRepositories(basePackages = "com.chengsy.code.tools.moxi.mongo")
public class MongoConfig1 {

    @Bean(name = "channelProperties")
    @ConfigurationProperties(prefix = "spring.data.mongodb.channel")
    public MongoProperties mongo1Properties() {
        return new MongoProperties();
    }

    @Bean(name = "channelTemplate")
    public MongoTemplate channelTemplate(@Qualifier("channelMongoFactory")MongoDatabaseFactory factory, MongoMappingContext context, BeanFactory beanFactory) {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(factory);
        MappingMongoConverter mappingConverter = new MappingMongoConverter(dbRefResolver, context);
        mappingConverter.setCustomConversions(beanFactory.getBean(MongoCustomConversions.class));
        //去掉_class字段
        mappingConverter.setTypeMapper(new DefaultMongoTypeMapper(null));

        return new MongoTemplate(channelMongoFactory(mongo1Properties()), mappingConverter);
    }

    @Bean(name = "channelMongoFactory")
    public MongoDatabaseFactory channelMongoFactory(@Qualifier("channelProperties") MongoProperties mongoProperties) {
        return new SimpleMongoClientDatabaseFactory(mongoProperties.getUri());
    }

}
