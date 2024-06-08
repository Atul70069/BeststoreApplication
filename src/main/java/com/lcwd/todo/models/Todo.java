package com.lcwd.todo.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "jpa_Todos")
public class Todo {

    @Id
    private int id;

    @Column(name = "todo_Title",length = 100)
    private String tittle;

    @Column(name = "todo_content",length = 1000)
    private String content;

    @Column(name = "todo_status",length = 10)
    private String status;

    @Column(name ="todo_added_date")
    private Date addeddate;

    @Column(name = "todo_date")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date todoDate;

    public Todo(int id, String tittle, String content, String status,Date addeddate,Date todoDate)
    {
        this.id = id;
        this.tittle = tittle;
        this.content = content;
        this.status = status;
        this.todoDate=todoDate;
        this.addeddate=addeddate;
    }

    public Todo()
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getAddeddate() {
        return addeddate;
    }

    public void setAddeddate(Date addeddate) {
        this.addeddate = addeddate;
    }

    public Date getTodoDate() {
        return todoDate;
    }

    public void setTodoDate(Date todoDate) {
        this.todoDate = todoDate;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", tittle='" + tittle + '\'' +
                ", content='" + content + '\'' +
                ", status='" + status + '\'' +
                ", addeddate=" + addeddate +
                ", todoDate=" + todoDate +
                '}';
    }
}
