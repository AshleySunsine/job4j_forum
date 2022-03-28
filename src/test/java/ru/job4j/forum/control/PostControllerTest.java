package ru.job4j.forum.control;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class PostControllerTest {

    @MockBean
    private PostService posts;

    @Autowired
    private MockMvc mockMvc;

    @Before
    void refrash() {

    }

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

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessageCreate() throws Exception {
        this.mockMvc.perform(post("/create")
                        .param("name","Куплю ладу-грант. Дорого."))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
        verify(posts).create(argument.capture());
        assertThat(argument.getValue().getName(), is("Куплю ладу-грант. Дорого."));
    }

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessageUpdate() throws Exception {
        this.mockMvc.perform(post("/create")
                        .param("name","Куплю ладу-грант. Дорого."))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
        verify(posts).create(argument.capture());
        assertThat(argument.getValue().getName(), is("Куплю ладу-грант. Дорого."));

        Long id = argument.getValue().getId();
        this.mockMvc.perform(get("/setposts/{id}", id)
                .param("description", "Куплю. Дорого.")
                        .param("name","Куплю ладу-грант. Дорого.")
                        .param("id", String.valueOf(id)))
                .andDo(print())
                .andExpect(status().is3xxRedirection());

        ArgumentCaptor<Post> argument2 = ArgumentCaptor.forClass(Post.class);
        verify(posts).setPost(argument2.capture());
        assertThat(argument2.getValue().getDescription(), is("Куплю. Дорого."));
    }

    @Test
    @WithMockUser
    void deleteReference() throws Exception {
        this.mockMvc.perform(get("/delete/{id}", 0))
                .andExpect(status().is3xxRedirection())
                .andDo(print());
    }

}