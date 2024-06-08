package com.lcwd.todo.Services.Impl;

import com.lcwd.todo.Services.TodoService;
import com.lcwd.todo.dao.TodoRepositry;
import com.lcwd.todo.models.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
@Primary
public class TodoJpaserviceImpl implements TodoService
{
    @Autowired
    private TodoRepositry todoRepositry;

    @Override
    public Todo createTodo(Todo todo) {
        return todoRepositry.save(todo);
    }

    @Override
    public Todo getTodo(int todoId) throws ParseException {
        return todoRepositry.findById(todoId).orElseThrow(() -> new RuntimeException("TDOD NOT FOUND "));
    }

    @Override
    public List<Todo> getAllTodos() {
        return todoRepositry.findAll();

    }

    @Override
    public Todo updateTodo(int todoId, Todo todo) {
        Todo todo1 = todoRepositry.findById(todoId).orElseThrow(() -> new RuntimeException("TDOD NOT FOUND "));
        todo1.setTittle(todo.getTittle());
        todo1.setContent(todo.getContent());
        todo1.setStatus(todo.getStatus());
        todo1.setTodoDate(todo.getTodoDate());

        return todoRepositry.save(todo);

    }

    @Override
    public void deleteTodo(int todoId) {
        Todo todo1 = todoRepositry.findById(todoId).orElseThrow(() -> new RuntimeException("TDOD NOT FOUND "));

        todoRepositry.delete(todo1);

    }
}
