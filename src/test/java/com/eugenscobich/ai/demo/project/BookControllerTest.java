package com.eugenscobich.ai.demo.project;

import com.eugenscobich.ai.demo.project.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    void testGetBooks() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/books"))
            .andExpect(status().isOk())
            .andReturn();
        String json = mvcResult.getResponse().getContentAsString();
        Book[] books = objectMapper.readValue(json, Book[].class);
        assertThat(books, is(not(emptyArray())));
        assertThat(books.length, is(3));
    }
}
