package com.lcwd.todo.Services.Impl;


import com.lcwd.todo.dao.TodoDao;
import com.lcwd.todo.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TodoServiceImpl implements com.lcwd.todo.Services.TodoService {
    private  TodoDao todoDao;
    //create to

    Logger logger= LoggerFactory.getLogger(TodoServiceImpl.class);

    //Thois list is acting as a fake DB for practise only ....
    List<Todo> todos= new ArrayList<>();

//    @Autowired
//    public TodoServiceImpl(TodoDao todoDao) {
//        this.todoDao = todoDao;
//    }

    public Todo createTodo(Todo todo)
    {
        //creating here  u can change logic asper DB

        todos.add(todo);
        logger.info("Todos {}",this.todos);
        return todo;

    }

    public List<Todo> getAllTodos() {
        return todos;
    }

    public Todo getTodo(int todoId)
    {
       Todo todo= todos.stream().filter(t->todoId==t.getId()).findAny().get();
        logger.info("TODO : {}",todo );
        return todo;
    }

    public Todo updateTodo(int todoId,Todo todo)
    {
        List<Todo> newUpdatList = todos.stream().map(t -> {
            if (t.getId() == todoId) {
                //after updating

                t.setTittle(todo.getTittle());
                t.setContent(todo.getContent());
                t.setStatus(todo.getStatus());
                return t;
            } else {
                return t;
            }
        }).collect(Collectors.toList());

        todos= newUpdatList;
        todo.setId(todoId);
        todos=newUpdatList;
        return todo;
    }

    public void deleteTodo(int todoId)
    {
        List<Todo> newList = todos.stream().filter(t -> t.getId() != todoId).collect(Collectors.toList());
        todos=newList;

    }

}
