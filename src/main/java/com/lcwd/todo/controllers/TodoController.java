package com.lcwd.todo.controllers;

import com.lcwd.todo.Services.Impl.TodoServiceImpl;
import com.lcwd.todo.Services.TodoService;
import com.lcwd.todo.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/todos")
public class TodoController {
    //CREATE
    Logger logger= LoggerFactory.getLogger(TodoController.class);

    Random random= new Random();
    @Autowired
    private TodoService todoService;
    private Todo todo;

    //String s=null;

//    @Autowired
//    public TodoController(TodoService todoService) {
//        this.todoService = todoService;
//    }

    @PostMapping
    public ResponseEntity<Todo> createTodoHandler(@RequestBody Todo todo)
    {// creating ID
        //logger.info("{}",s.length());
        int id= random.nextInt(999996);
        todo.setId(id);


// craete date
        Date currentDate= new Date();
        logger.info("current date: {}",currentDate);
        logger.info("Todo date :{}",todo.getTodoDate());

        todo.setAddeddate(currentDate);

//...........CREATING TODOD***********************

        logger.info("Create TODO");
        //call service to create TODO
        Todo todo1= todoService.createTodo(todo);
        return new ResponseEntity<>(todo1, HttpStatus.CREATED);

    }

    //get all todos

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodoHandlers()
    {
       List<Todo> alltodos= todoService.getAllTodos();
       return new ResponseEntity<>(alltodos,HttpStatus.OK);
    }


    //SINGLE TDOD
    @GetMapping("/{todoId}")
    public ResponseEntity<Todo> getSingleTodoHandler(@PathVariable int todoId) throws ParseException {
     Todo todo=   todoService.getTodo(todoId);
        return ResponseEntity.ok(todo);
    }
    
    
    //UPDATE
    
    @PutMapping("/{todoId}")
    public ResponseEntity<Todo> updateTodoHandler(@RequestBody Todo todoWithnewDetails,@PathVariable int todoId)
    {
        todoService.updateTodo(todoId,todoWithnewDetails);
        return ResponseEntity.ok(todo);
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<String>deleteTodo(@PathVariable int todoId)
    {
        logger.info("Deleteing TODOS");

        todoService.deleteTodo(todoId);
        return ResponseEntity.ok("TODO DELETED");
    }



    @ExceptionHandler
    public ResponseEntity<String> nullpointerException(NullPointerException ex)
    {
        System.out.println(ex.getMessage());
        System.out.println("Null pointer Exception");
        return  new ResponseEntity<>("nULL POINTER "+ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
