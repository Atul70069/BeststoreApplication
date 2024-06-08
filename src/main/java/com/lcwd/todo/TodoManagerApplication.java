package com.lcwd.todo;

import com.lcwd.todo.dao.TodoDao;
import com.lcwd.todo.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class TodoManagerApplication implements CommandLineRunner {

	@Autowired
	private TodoDao todoDao;

	Logger logger= LoggerFactory.getLogger(TodoManagerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TodoManagerApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
//		System.out.println("Apps started");
//		JdbcTemplate template = todoDao.getTemplate();
//
//		logger.info("template object:{}",template);
//		logger.info("template object:{}",template.getDataSource().);


		//INSERTING IN DATABASE
//		Todo todo= new Todo();
//		todo.setId(2);
//		todo.setTittle("Spring DEVELOPER ");
//		todo.setContent("LEARNING");
//		todo.setStatus("DONE");
//		todo.setAddeddate(new Date());
//		todo.setTodoDate( new Date());
//		todoDao.saveTodo(todo);

		// GETTING SINGLE TODOS

//		Todo todo = todoDao.getTodo(1234);
//		logger.info("Todo: {}",todo);
//
//		//UPDATING
//		todo.setTittle("JAVA SPRING BOOT COUSRE");
//    	todo.setContent(" larn this ");
//    	todo.setStatus("COMPLETE");
//	    todo.setAddeddate(new Date());
//	    todo.setTodoDate( new Date());
//		todoDao.updateTodo(1234,todo);


//		GETTING ALL TODOS
//		List<Todo> alltodos = todoDao.getAlltodos();
//		logger.info("ALL TODOS: {}",alltodos);

		//DELETE

//		todoDao.deleteTodo(123);


		//MULTIPLE DELETE

		//todoDao.deleteUltiple( new int[]{1,2});

	}
}

