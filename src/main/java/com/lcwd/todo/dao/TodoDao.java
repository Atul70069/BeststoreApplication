package com.lcwd.todo.dao;

import com.lcwd.todo.helper.Helper;
import com.lcwd.todo.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class TodoDao {

    Logger logger= LoggerFactory.getLogger(TodoDao.class);




   private JdbcTemplate template;

    public TodoDao(@Autowired JdbcTemplate template) {
        this.template = template;

        //creaet Table .....if doesnt exist in the DATABASE.

        String createTable="create table IF NOT EXISTS todos(id int primary key,tittle varchar(100) not null,content varchar(500),status varchar(10) not null,addeddate datetime,todoDate datetime )";

      int update=  template.update(createTable);
        logger.info("TODO TABEL CREATED {}",update);
    }




    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }


    //....save todo in databdase

    public Todo saveTodo(Todo todo )
    {
     String insertQuery="insert into todos(id,tittle,content,status,addeddate,todoDate) values(?,?,?,?,?,?) ";

        int rows= template.update(insertQuery,todo.getId(),todo.getTittle(),todo.getContent(),todo.getStatus(),
                todo.getAddeddate(),todo.getTodoDate());

        logger.info("JDBC OPERATION {} insetted:",rows);

        return todo;
    }




    //get single todo in DB
    public Todo getTodo(int id ) throws ParseException {
        String query="select * from todos where id= ?";
        //Map<String, Object> todoData = template.queryForObject(query,)
//        logger.info("Todo: {}",todoData);

//        Todo todo=new Todo();
//        todo.setId((int)(todoData.get("id")));
//        todo.setTittle((String)(todoData.get("tittle")));
//        todo.setContent((String)(todoData.get("content")));
//        todo.setStatus((String)(todoData.get("status")));
//        todo.setAddeddate(Helper.parseDate((LocalDateTime) todoData.get("addedDate")));
//        todo.setTodoDate(Helper.parseDate((LocalDateTime) todoData.get("todoDate")));
//        return todo;

        Todo todo = template.queryForObject(query, new TodoRowMapper(), id);
        return todo;



    }



    //...........get all todo from DB*******************

    public List<Todo> getAlltodos()
    {
        String query="select * from todos";

        List<Todo> todo = template.query(query, new TodoRowMapper());


        //List<Map<String, Object>> maps = template.queryForList(query);
//        List<Todo> todos = maps.stream().map((map) -> {
//            Todo todo = new Todo();
//            todo.setId((int) (map.get("id")));
//            todo.setTittle((String) (map.get("tittle")));
//            todo.setContent((String) (map.get("content")));
//            todo.setStatus((String) (map.get("status")));
//            try {
//                todo.setAddeddate(Helper.parseDate((LocalDateTime) map.get("addedDate")));
//                todo.setTodoDate(Helper.parseDate((LocalDateTime) map.get("todoDate")));
//            } catch (ParseException e) {
//                throw new RuntimeException(e);
//            }
//            return todo;
//        }).collect(Collectors.toList());

        return todo;

    }


    //UPDATE todo

    public Todo updateTodo(int id ,Todo newtodo)
    {
        String query= "UPDATE todos set tittle=?,content=?,status=?,addedDate=?,todoDate=? where id=?";
        int update = template.update(query,newtodo.getTittle(),newtodo.getContent(),newtodo.getStatus(),newtodo.getAddeddate(),newtodo.getTodoDate(),id);
        logger.info("UPDATING THE TABLE :{}",update);
        newtodo.setId(id);

        return newtodo;
    }


    //delete single TODO FROM DB
    public void deleteTodo(int id)
    {
        String query="delete from todos where id=?";
       int update= template.update(query,id);
        logger.info("DELETING IN TODOS:{}",update);
    }

 // delete multiple todo from DATABASE

    public void deleteUltiple(int ids[])
    {
        String query="delete from todos where id=?";
        int [] ints=template.batchUpdate(query, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                int id=ids[i];
                ps.setInt(1,id);
            }

            @Override
            public int getBatchSize() {
                return ids.length;
            }
        });

        for (int i:ints){
            logger.info("DELTED {}",i);
    }

    }


}
