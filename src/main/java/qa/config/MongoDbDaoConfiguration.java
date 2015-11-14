package qa.config;

import com.mongodb.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.query.MongoParameters;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import qa.dao.TagDao;
import qa.dao.UserDao;
import qa.dao.VoteDao;
import qa.dao.WordsDao;

import javax.annotation.PreDestroy;
import javax.net.SocketFactory;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;

/**
 * Created by john on 15-11-12.
 */
@Configuration
@ComponentScan
@EnableMongoRepositories
@EnableMongoAuditing
@EnableTransactionManagement

public class MongoDbDaoConfiguration  extends AbstractMongoConfiguration{
    @Bean
    public UserDao userDao(MongoTemplate template) {
        return new UserDao(template);
    }

    @Bean
    public VoteDao voteDao(MongoTemplate template) {
        return new VoteDao(template);
    }

    @Bean
    public WordsDao wordsDao(MongoTemplate template) {
        return new WordsDao(template);
    }

    @Bean
    public TagDao tagDao(MongoTemplate template) {
        return new TagDao(template);
    }

    @Override
    @Bean
    protected String getDatabaseName() {
        return "qadb";
    }

    @PreDestroy
    public void close() {
//          关闭 链接
    }

    @Override
    @Bean
    public Mongo mongo() throws Exception {
//
        return new MongoClient(singletonList(new ServerAddress("222.30.64.156", 27017)),
                singletonList(MongoCredential.createCredential("test", "qadb", "a6de521abefc2fed4f5876855a3484f5".toCharArray())));
    }
// "pwd" : "a6de521abefc2fed4f5876855a3484f5"
        @Bean

    public MongoClient mongoClient(MongoClientOptions clientOptions) {
        List<MongoCredential> credentials = Arrays.asList(MongoCredential.createCredential(
                "test", "qadb", "a6de521abefc2fed4f5876855a3484f5".toCharArray()
        ));
        List<ServerAddress> serverAddresses = Arrays.asList(new ServerAddress("222.30.64.156", 27017));
        return new MongoClient(serverAddresses, credentials, clientOptions);
    }

    @Bean
    public MongoClientOptions.Builder builder() {
        return  MongoClientOptions.builder()
               .            alwaysUseMBeans(true)
                .connectionsPerHost(20)
                .connectTimeout(60 * 60)
                .description("welcome to  here!")
                .socketFactory(SocketFactory.getDefault())
                .socketKeepAlive(true)
                .sslEnabled(true)
                .writeConcern(WriteConcern.SAFE);
    }

    @Bean
    public MongoClientOptions mongoClientOptions(MongoClientOptions.Builder builder) {
        return builder.build();
    }
//    @Override
//    @Bean
//    public Mongo mongo() throws Exception {
//        Mongo mongo = new MongoClient("222.30.64.156:27017");
//        mongo.setWriteConcern(WriteConcern.SAFE);
//
//        return mongo;
//    }

    @Override
    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return super.mongoTemplate();
    }

    @Override
    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
        return super.mongoDbFactory();
    }
    @Override
    @Bean
    protected String getMappingBasePackage() {
        return "qa.domain";
    }

    @Override
    @Bean
    protected UserCredentials getUserCredentials() {
        return new UserCredentials("j.yao", "eb92d7045bdc1059bb77303b9089bb0f");
    }
}
