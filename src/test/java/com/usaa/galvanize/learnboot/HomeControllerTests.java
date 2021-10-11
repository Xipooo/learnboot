package com.usaa.galvanize.learnboot;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTests {

    @MockBean
    private ToDoRepository toDoRepository;

    @Test
    void listCallsFindAllMethodOnToDoRepository() {
        HomeController SUT = new HomeController(toDoRepository);

        SUT.list();

        verify(toDoRepository).findAll();
    }
}
