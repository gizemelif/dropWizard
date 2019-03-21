package com.gizem.api;

import javax.persistence.*;




@Entity
@Table(name="TodoItem")
@NamedQueries({
        @NamedQuery(name = "com.gizem.api.items.findAll",
                query = "select e from TodoItem e" ),

        @NamedQuery(
        name="com.gizem.api.items.deleteById",
        query = "delete TodoItem t where t.id= :id")

})
public class TodoItem {
    @Column(name="name")
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "completed")
    private boolean completed;

    public TodoItem(){}

    public TodoItem(String name){
        this.name = name;
    }

    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return this.name;
    }
    public void setCompleted(boolean completed){
        this.completed = completed;
    }
    public boolean getCompleted(){
        return this.completed;
    }
    public void setId(int id){
        this.id=id;
    }
    public int getId(){
        return this.id;
    }


}
