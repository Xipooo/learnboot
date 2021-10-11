package com.usaa.galvanize.learnboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

// As Harry the husband
// I want to see a list of items I need to do
// So that I can plan my day

@SpringBootTest
@AutoConfigureMockMvc
public class HomeRouteTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ToDoRepository toDoRepository;

    @Test
    void getRequestAtHomeRouteShouldReturnOkResult() throws Exception {
        // GIVEN the application is running and there are no ToDo items
        // WHEN a GET request is made to the home route
        ResultActions result = mockMvc.perform(get("/"));

        // THEN the response should return an OK result
        result.andExpect(status().isOk());
    }

    @Test
    void getRequestToHomeRouteShouldReturnEmptyArrayWhenNoToDoItemsExist() throws Exception {
        // GIVEN the application is running and there are no ToDo Items
        // WHEN a GET request is made to the home route
        ResultActions result = mockMvc.perform(get("/"));

        // THEN an empty array should be returned
        result.andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void getRequestToHomeRouteShouldReturnOneToDoItemWhenOneToDoItemExists() throws Exception {
        // GIVEN the application is running and there is one ToDo item
        ToDoItem watchNeverEndingStory = new ToDoItem(1L, "Watch never ending story.");
        toDoRepository.save(watchNeverEndingStory);

        // WHEN a GET request is made to the home route
        ResultActions result = mockMvc.perform(get("/"));

        // THEN the results should contain the list of records returned by the database
        result.andExpect(jsonPath("[0].description").value(watchNeverEndingStory.getDescription()));

    }

    @Test
    void foo() throws Exception {
        // GIVEN the application is running and there are multiple items
        ToDoItem watchNeverEndingStory = new ToDoItem(1L, "Watch never ending story.");
        ToDoItem takeOutTrash = new ToDoItem(2L, "Take out trash.");
        ToDoItem teachJavaTDD = new ToDoItem(3L, "Teach Java TDD class.");
        List<ToDoItem> toDoItems = new ArrayList<>(List.of(watchNeverEndingStory, takeOutTrash, teachJavaTDD));
        toDoRepository.saveAll(toDoItems);
        TypeReference<List<ToDoItem>> mapType = new TypeReference<List<ToDoItem>>(){};

        // WHEN a GET request is made to the home route
        List<ToDoItem> content = new ObjectMapper()
                .readValue(mockMvc.perform(get("/"))
                .andReturn()
                .getResponse()
                .getContentAsString(), mapType);
        
        // THEN the results should contain the list of records returned by the database
        assertEquals(toDoItems.size(), content.size());
    }

    // GIVEN the application is running and there are 11 items
    // WHEN a GET request is made to the home route
    // THEN the results should contain a list of only 10 items returned by the
    // database
}
