package qa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.crypto.password.PasswordEncoder;
import qa.dao.QuestionDao;
import qa.dao.UserDao;
import qa.dao.VoteDao;
import qa.service.*;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ServiceConfiguration {
    @Bean
    public QuestionService questionService(QuestionDao questionDao, VoteService voteService) {
        return new QuestionService(questionDao, voteService);
    }

    @Bean
    public UserService userService(UserDao userDao, PasswordEncoder passwordEncoder) {
        UserService service = new UserService(userDao);
        service.setPasswordEncoder(passwordEncoder);
        return service;
    }

    @Bean
    public VoteService voteService(VoteDao voteDao) {
        return new VoteService(voteDao);
    }

    @Bean
    public PrivilegeCheckingService privilegeService() {
        return new PrivilegeCheckingService();
    }

    @Bean
    public PointsService pointsService() {
        return new PointsService();
    }
}
