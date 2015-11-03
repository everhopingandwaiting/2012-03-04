package qa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import qa.dao.QuestionDao;
import qa.dao.UserDao;
import qa.service.PrivilegeCheckingService;
import qa.service.QuestionService;
import qa.service.UserService;

@Configuration
public class ServiceConfiguration {
    @Bean
    public QuestionService questionService(QuestionDao questionDao) {
        return new QuestionService(questionDao);
    }

    @Bean
    public UserService userService(UserDao userDao, PasswordEncoder passwordEncoder) {
        UserService service = new UserService(userDao);
        service.setPasswordEncoder(passwordEncoder);
        return service;
    }

    @Bean
    public PrivilegeCheckingService privilegeService() {
        return new PrivilegeCheckingService();
    }
}
