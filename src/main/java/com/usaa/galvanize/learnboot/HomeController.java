package com.usaa.galvanize.learnboot;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private ToDoRepository toDoRepository;

    public HomeController(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    @GetMapping("/")
    public List<ToDoItem> list() {
        return toDoRepository.findAll();
    }
}
