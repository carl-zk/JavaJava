package org.auto.scan;

import org.springframework.stereotype.Component;

/**
 * Created by hero on 16-5-21.
 */
public class Skey {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void rain(){
        System.out.println("下雨了");
    }
}
