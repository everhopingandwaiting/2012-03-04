package qa.web;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import qa.config.DaoConfiguration;
import qa.config.ServiceConfiguration;
import qa.config.WebConfiguration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DaoConfiguration.class,
        ServiceConfiguration.class, WebConfiguration.class})
@WebAppConfiguration
//@TestExecutionListeners({DbUnitTestExecutionListener.class,
//        DependencyInjectionTestExecutionListener.class})
@ActiveProfiles("dev")
public class QuestionControllerIntegrationTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mvc;

    @Before
    public void SetUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
//    @DatabaseSetup("classpath:sampleData.xml")
    public void testAddOneQuestion() throws Exception {
        mvc.perform(post("/question").param("title", "标题2").param("content", "内容2"))
                .andExpect(handler().handlerType(QuestionController.class))
                .andExpect(handler().methodName("addOneQuestion"))
                .andExpect(status().is(200))
                .andExpect(view().name("askquestion"));
    }

    @Test
//    @DatabaseSetup("classpath:sampleData.xml")
    public void testSuccessfullyAddOneQuestion() throws Exception {
        mvc.perform(post("/question").param("title", "标题2标题2标题2标题2标题2标题2标题2")
                .param("content", "内容2标题2标题2标题2标题2标题2标题2标题2标题2标题2标题2标题2标题2标题2标题2标题2" +
                        "标题2标题2标题2标题2标题2标题2标题2标题2标题2标题2标题2标题2标题2标题2标题2标题2标题2标题2标题2" +
                        "标题2标题2标题2标题2标题2标题2标题2标题2标题2标题2标题2标题2标题2")
                .characterEncoding("utf-8"))
                .andExpect(handler().handlerType(QuestionController.class))
                .andExpect(handler().methodName("addOneQuestion"))
                .andExpect(status().is(302))
                .andExpect(redirectedUrl("/questions"))
                .andExpect(xpath("//a[@href='/qa-system/question/1']").string("标题2标题2标题2标题2标题2标题2标题2"))
                .andDo(print());
    }
}
