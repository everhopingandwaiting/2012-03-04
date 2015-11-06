package qa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import qa.dao.*;
import qa.service.*;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@PropertySource("classpath:setting.properties")
public class ServiceConfiguration {
    @Bean
    public QuestionService questionService(WordsDao wordsDao) {
        return new QuestionService(wordsDao);
    }

    @Bean
    public UserService userService(UserDao userDao, PasswordEncoder passwordEncoder) {
        UserService service = new UserService(userDao);
        service.setPasswordEncoder(passwordEncoder);
        return service;
    }

    @Bean
    public TagService tagService(TagDao tagDao) {
        return new TagService(tagDao);
    }

    @Bean
    public VoteService voteService(VoteDao voteDao) {
        return new VoteService(voteDao);
    }

    @Bean
    public WordsService wordsService(WordsDao wordsDao) {
        return new WordsService(wordsDao);
    }

    @Bean
    public PrivilegeCheckingService privilegeService(Environment environment) {
        return new PrivilegeCheckingService(environment);
    }

    @Bean
    public PointsService pointsService(WordsDao wordsDao, Environment environment) {
        return new PointsService(wordsDao, environment);
    }

}
