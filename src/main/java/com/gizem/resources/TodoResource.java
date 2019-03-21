package com.gizem.resources;

import com.gizem.api.TodoItem;
import com.gizem.service.TodoService;
import io.dropwizard.hibernate.UnitOfWork;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.UpdateTimestamp;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/todo")
@Produces(MediaType.APPLICATION_JSON)
public class TodoResource {

    private TodoService service;
    private static SessionFactory factory;

    public TodoResource(TodoService service) {
        this.service = service;
    }


    @POST
    @UnitOfWork
    public TodoItem createItem(@FormParam("name") String name) {
        TodoItem todoItem = new TodoItem(name);
        return service.add(todoItem);
    }

    @GET
    @UnitOfWork
    public List<TodoItem> all() {
        return service.getAll();
    }
    @DELETE
    @Path("{id}")
    @UnitOfWork
    public Integer deleteById(@PathParam("id") int id){
        return service.deleteById(id);
    }
    @PUT
    @Path("/")
    @UnitOfWork
    public Integer update(@PathParam("id") Integer id, @FormParam("name") String name){
        return service.update(id, name);
    }
    @PUT
    @UnitOfWork
    public Integer updateItem(@PathParam("id") Integer id, @FormParam("name") String name){
        return service.updateItem(id, name);
    }
    @PUT
    @Path("{id}")
    @UnitOfWork
    public Integer updateProcess(@PathParam("id") Integer id, @FormParam("name") String name){
        return service.updateProcess(id,name);
    }
}
