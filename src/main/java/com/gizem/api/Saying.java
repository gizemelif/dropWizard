package com.gizem.api;

import org.hibernate.validator.constraints.Length;

public class Saying {
    private long id;
    @Length(max = 3)
    private String content;

    public Saying(){
        //Jackson deserialization
    }
    public Saying(long id, String content){
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
