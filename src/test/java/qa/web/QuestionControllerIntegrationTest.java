package qa.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import qa.domain.Question;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebIntegrationTest
public class QuestionControllerIntegrationTest {
    @Autowired
    private WebApplicationContext appContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(appContext)
                .apply(springSecurity()).build();
    }

    @Test
    public void testShowAskQuestionForm() throws Exception {
        mockMvc.perform(get("/question/ask").with(user("zhangsan")))
                .andExpect(handler().handlerType(QuestionController.class))
                .andExpect(handler().methodName("showAskQuestionForm"))
                .andExpect(view().name("question/ask"))
                .andExpect(model().attribute("question", equalTo(new Question())))
                .andExpect(status().isOk());
    }

}
