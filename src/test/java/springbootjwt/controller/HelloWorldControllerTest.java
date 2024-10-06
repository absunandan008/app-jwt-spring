package springbootjwt.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;



//This will fail because we added spring security
@WebMvcTest(HelloWorldController.class)
public class HelloWorldControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getGreetingAPI() throws Exception {
        mvc.perform(MockMvcRequestBuilders
            .get("/greeting")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())  // Check if status is 200 OK
            .andExpect(content().string("Hello from the other side"));  // Check if response body is correct
    }
}

