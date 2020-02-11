package com.laylasahara.todoapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TodoAppApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    void homepageTest() throws Exception {
            this.mvc.perform(get("/todos"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("index"));
    }

    @Test
    void createTodoRequestTest() throws Exception {

            this.mvc.perform(get("/todo/add"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("create_todo"));
            this.mvc.perform(get("/todo/add"))
                    .andExpect(status().isOk())
                    .andExpect(model().attributeExists("todo"));
    }

    @Test
    void createTodoPostTest() throws Exception {
            this.mvc.perform(get("/todo/add"))
                    .andExpect(status().isOk())
                    .andExpect(model().attributeExists("todo"));
    }

    @Test
    void updateTodoGetTest() throws Exception {
        this.mvc.perform(get("/todo/update/2"))
                .andExpect(status().isOk())
                .andExpect(view().name("edit_todo"));
    }

    @Test
    void updateTodoPostTest() throws Exception {
        this.mvc.perform(post("/todo/update/2"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        this.mvc.perform(post("/todo/update/2"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }

    @Test
    void getTodoTest() throws Exception {
        this.mvc.perform(get("/todo/next/2"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/todo/2"));
    }

    @Test
    void filterTodosTest() throws Exception {
        this.mvc.perform(get("/todos/filter/PENDING"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
        this.mvc.perform(get("/todos/filter/PENDING"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("todos"));
    }

}
