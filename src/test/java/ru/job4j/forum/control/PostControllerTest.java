package ru.job4j.forum.control;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    void getAll() throws Exception {
        this.mockMvc.perform(get("/index"))
                .andExpect(model().attributeExists("posts"))
                .andExpect(model().attributeExists("posts"))
                .andDo(print());
    }

    @Test
    @WithMockUser
    void getPost() throws Exception {
        this.mockMvc.perform(get("/posts/{id}", 1))
                .andExpect(model().attributeExists("optional"))
                .andDo(print());
    }

    @Test
    @WithMockUser
    void setPost() throws Exception {
        this.mockMvc.perform(get("/posts/{id}", 1))
                .andExpect(model().attributeExists("optional"))
                .andDo(print());
    }

    @Test
    @WithMockUser
    void createReference() throws Exception {
        this.mockMvc.perform(get("/createPost"))
                .andExpect(forwardedUrl("/WEB-INF/views/create.jsp"))
                .andExpect(status().isOk())
                .andDo(print());
    }



}