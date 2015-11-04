package qa.web;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import qa.config.DaoConfiguration;
import qa.config.ServiceConfiguration;
import qa.config.WebConfiguration;
import qa.config.WebSecurityConfiguration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@ContextConfiguration(classes = {DaoConfiguration.class,
        ServiceConfiguration.class, WebConfiguration.class,
        WebSecurityConfiguration.class})
@WebAppConfiguration
@ActiveProfiles("dev")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
public @interface WebIntegrationTest {
}
