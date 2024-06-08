package com.lcwd.todo.dao;

import com.lcwd.todo.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepositry extends JpaRepository<Todo,Integer>
{

}
