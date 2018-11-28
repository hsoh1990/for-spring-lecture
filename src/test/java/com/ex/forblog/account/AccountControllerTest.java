package com.ex.forblog.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private AccountRepository accountRepository;

    private MockMvc mockMvc;

    private Account registerAccountDto;

    ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();

        registerAccountDto = new Account("ohs", "password", "ohs@gmail.com");
    }


    @Test
    public void registAccount() throws Exception {
        // Given

        // When
        final ResultActions resultActions = mockMvc.perform(
                post("/accounts").contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(this.registerAccountDto))
                        .with(csrf()));

        // Then
        resultActions.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").value(2))
                .andExpect(jsonPath("name").value("ohs"))
                .andExpect(jsonPath("password").value("password"))
                .andExpect(jsonPath("email").value("ohs@gmail.com"));

    }

    @Test
    public void getAllAccount() throws Exception {
        // Given

        // When
        final ResultActions resultActions = mockMvc.perform(get("/accounts"));

        // Then
        resultActions.andDo(print())
                .andExpect(status().isOk());

    }
}